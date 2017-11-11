package controllers;

import model.database.FailureMode;
import model.database.Tag;
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
                .findFailureModeEntityById(failureModeId)
                .thenApplyAsync(createAndAddTag(), executionContext.current())
                .thenComposeAsync(getResult(), executionContext.current());
    }

    private Function<Optional<FailureMode>, CompletionStage<FailureMode>> createAndAddTag() {
        return optionalFailureMode ->
                optionalFailureMode.map(failureMode ->
                        createTag().thenComposeAsync(
                                addTag(failureMode))).orElseThrow(EntityNotFoundException::new);
    }

    private CompletionStage<Tag> createTag() {
        return tagService.create(extractTagResourceFromJson(request()));
    }

    private Function<Tag, CompletionStage<FailureMode>> addTag(FailureMode failureMode) {
        return tag -> failureModeService.addTag(failureMode, tag);
    }

    private Function<CompletionStage<FailureMode>, CompletionStage<Result>> getResult() {
        return completionStage -> completionStage.thenApplyAsync(failureMode -> Results.created(Json.toJson(failureMode)));
    }
}
