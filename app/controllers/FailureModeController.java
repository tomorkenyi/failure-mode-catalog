package controllers;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import services.FailureModeService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static java.util.stream.Collectors.toList;
import static util.FailureModeUtil.extractFailureModeResourceFromJson;

public class FailureModeController extends Controller {

    private final FailureModeService service;
    private final HttpExecutionContext executionContext;

    @Inject
    public FailureModeController(FailureModeService service, HttpExecutionContext executionContext) {
        this.service = service;
        this.executionContext = executionContext;
    }

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
