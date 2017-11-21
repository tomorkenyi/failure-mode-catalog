package controller;

import model.presentation.FailureModeResource;
import play.Logger;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Result;
import play.mvc.Results;
import service.FailureModeService;
import service.TagService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;
import static util.TagUtil.extractTagResourceFromJson;

public class TagController {

    @Inject
    private TagService tagService;
    @Inject
    private FailureModeService failureModeService;
    @Inject
    private HttpExecutionContext executionContext;

    public CompletionStage<Result> create(Long failureModeId) {
        return tagService.create(failureModeId, extractTagResourceFromJson(request()))
                .thenApplyAsync(optionalTag ->
                        optionalTag.map(tag ->
                                ok(Json.toJson(tag))).orElseGet(() ->
                                Results.internalServerError("FailureMode not found with id: " + failureModeId)));
    }

    private Function<CompletionStage<FailureModeResource>, CompletionStage<Result>> getResult() {
        Logger.debug("Get the result of adding tag to failuremode and making a JSON response out of it");
        return completionStage -> completionStage.thenApplyAsync(failureModeResource ->
                Results.created(Json.toJson(failureModeResource)));
    }

    public CompletionStage<Result> search(String tag) {
        return tagService
                .search(tag)
                .thenApplyAsync(optionalTagResource ->
                        optionalTagResource.map(tagResource ->
                                ok(Json.toJson(tagResource))).orElseGet(Results::notFound));
    }


    public CompletionStage<Result> delete(Long failureModeId, Long tagId) {
        return tagService
                .delete(failureModeId, tagId)
                .thenApplyAsync(aVoid ->
                        ok("Tag with id: " + tagId + " is removed from failure mode: " + failureModeId));

    }
}
