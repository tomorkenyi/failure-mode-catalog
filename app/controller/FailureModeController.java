package controller;

import static java.util.stream.Collectors.toList;
import static util.FailureModeUtil.extractFailureModeResourceFromJson;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import service.FailureModeService;

public class FailureModeController extends Controller {

    @Inject
    private FailureModeService service;
    @Inject
    private HttpExecutionContext executionContext;

    public Result index() {
        return ok("Home page");
    }

    public CompletionStage<Result> list() {
        return service.findAll().thenApplyAsync(failureModeStream ->
                ok(Json.toJson(failureModeStream.collect(toList()))), executionContext.current()
        );
    }

    public CompletionStage<Result> get(Long id) {
        return service.findById(id).thenApplyAsync(optionalFailureModeResource ->
                optionalFailureModeResource.map(resource -> ok(Json.toJson(resource))).orElseGet(Results::notFound));
    }

    public CompletionStage<Result> create() {
        return service.create(extractFailureModeResourceFromJson(request())).thenApplyAsync(failureMode ->
                created(Json.toJson(failureMode)), executionContext.current()
        );
    }

    public CompletionStage<Result> update(Long id) {
        return service.update(id, extractFailureModeResourceFromJson(request())).thenApplyAsync(optionalFailureMode ->
                optionalFailureMode.map(failureMode -> ok(Json.toJson(failureMode))).orElseGet(Results::notFound));
    }
}
