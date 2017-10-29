package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.FailureModeResource;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import services.FailureModeService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static java.util.stream.Collectors.toList;

public class FailureModeController extends Controller {

    private final FailureModeService service;
    private final HttpExecutionContext executionContext;

    @Inject
    public FailureModeController(FailureModeService service, HttpExecutionContext executionContext) {
        this.service = service;
        this.executionContext = executionContext;
    }

    public Result index() {
        return TODO;
    }

    public CompletionStage<Result> list() {
        return service.findAll().thenApplyAsync(failureModeStream ->
                ok(Json.toJson(failureModeStream.collect(toList()))), executionContext.current()
        );
    }

    public CompletionStage<Result> create() {
        JsonNode json = request().body().asJson();
        final FailureModeResource resource = Json.fromJson(json, FailureModeResource.class);
        return service.create(resource).thenApplyAsync(failureMode ->
                created(Json.toJson(failureMode)), executionContext.current()
        );
    }

    public Result get(Long id) {
        return TODO;
    }

    public Result update(Long id) {
        return TODO;
    }

}
