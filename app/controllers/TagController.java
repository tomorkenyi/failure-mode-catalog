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
                .thenApplyAsync(addTagStage(), executionContext.current())
                .thenComposeAsync(getResultStage(), executionContext.current());
    }

    private Function<FailureMode, CompletionStage<FailureMode>> addTagStage() {
        return failureMode -> tagService.create(extractTagResourceFromJson(request())).thenComposeAsync(addTag(failureMode));
    }

    private Function<Tag, CompletionStage<FailureMode>> addTag(FailureMode failureMode) {
        return tag -> failureModeService.addTag(failureMode, tag);
    }

    private Function<CompletionStage<FailureMode>, CompletionStage<Result>> getResultStage() {
        return completionStage -> completionStage.thenApplyAsync(failureMode -> Results.created(Json.toJson(failureMode)));
    }
}
