package controllers;

import model.database.Tag;
import model.presentation.FailureModeResource;
import play.Logger;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Result;
import play.mvc.Results;
import services.FailureModeService;
import services.TagService;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static play.mvc.Controller.request;
import static util.TagUtil.extractTagResourceFromJson;

public class TagController {

    private final TagService tagService;
    private final FailureModeService failureModeService;
    private final HttpExecutionContext executionContext;

    @Inject
    public TagController(TagService tagService, FailureModeService failureModeService, HttpExecutionContext executionContext) {
        this.tagService = tagService;
        this.failureModeService = failureModeService;
        this.executionContext = executionContext;
    }

    public Result search(String tag) {
        return play.mvc.Results.TODO;
    }


    public CompletionStage<Result> create(Long failureModeId) {
        return failureModeService
                .findById(failureModeId)
                .thenApplyAsync(createAndAddTag(), executionContext.current())
                .thenComposeAsync(getResult(), executionContext.current());
    }

    private Function<Optional<FailureModeResource>, CompletionStage<FailureModeResource>> createAndAddTag() {
        return optionalFailureModeResource ->
                optionalFailureModeResource.map(failureModeResource ->
                        createTag().thenComposeAsync(
                                addTag(failureModeResource))).orElseThrow(EntityNotFoundException::new);
    }

    private CompletionStage<Tag> createTag() {
        Logger.debug("Creating tag from request: " + request().body().asText());
        return tagService.create(extractTagResourceFromJson(request()));
    }

    private Function<Tag, CompletionStage<FailureModeResource>> addTag(FailureModeResource failureModeResource) {
        return tag -> {
            Logger.debug("Add tag: " + tag + " to failure mode: " + failureModeResource);
            return failureModeService.addTag(failureModeResource.getId(), tag);
        };
    }

    private Function<CompletionStage<FailureModeResource>, CompletionStage<Result>> getResult() {
        Logger.debug("Get the result of adding tag to failuremode and making a JSON response out of it");
        return completionStage -> completionStage.thenApplyAsync(failureModeResource ->
                Results.created(Json.toJson(failureModeResource)));
    }
}
