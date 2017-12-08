package controller;

import static util.FailureModeUtil.extractFailureModeResourceFromJson;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import org.reactivestreams.Publisher;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import akka.util.ByteString;
import model.presentation.FailureModeResource;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import rx.Observable;
import rx.RxReactiveStreams;
import service.FailureModeService;

public class FailureModeController extends Controller {

    @Inject
    private FailureModeService service;

    @Inject
    private HttpExecutionContext executionContext;

    public CompletionStage<Result> get(Long failureModeId) {
        return service.findById(failureModeId).thenApplyAsync(optionalFailureModeResource ->
                optionalFailureModeResource.map(resource -> ok(Json.toJson(resource))).orElseGet(Results::notFound));
    }

    public CompletionStage<Result> create() {
        return service.create(extractFailureModeResourceFromJson(request())).thenApplyAsync(failureMode ->
                created(Json.toJson(failureMode)), executionContext.current()
        );
    }

    public CompletionStage<Result> update(Long failureModeId) {
        return service.update(failureModeId, extractFailureModeResourceFromJson(request())).thenApplyAsync(optionalFailureMode ->
                optionalFailureMode.map(failureMode -> ok(Json.toJson(failureMode))).orElseGet(Results::notFound));
    }

    public Result list() {

        Publisher<FailureModeResource> publisher = RxReactiveStreams.toPublisher(service.findAll());
        Source<ByteString, NotUsed> source = Source.fromPublisher(publisher).map(o -> ByteString.fromString(o.toString()));

        return ok().chunked(source).as("application/json");

        //        return fromObservable(service.findAll())
        //                .thenApplyAsync(failureModeResources ->
        //                        ok(Json.toJson(failureModeResources)), executionContext.current());
    }

    private static <T> CompletableFuture<List<T>> fromObservable(Observable<T> observable) {
        final CompletableFuture<List<T>> future = new CompletableFuture<>();
        observable
                .doOnError(future::completeExceptionally)
                .toList()
                .forEach(future::complete);
        return future;
    }
}
