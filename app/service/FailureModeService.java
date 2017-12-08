package service;

import static mapper.FailureModeMapper.INSTANCE;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import javax.inject.Inject;

import akka.actor.ActorSystem;
import mapper.CycleAvoidingMappingContext;
import model.database.FailureMode;
import model.presentation.FailureModeResource;
import play.libs.concurrent.HttpExecutionContext;
import repository.FailureModeRepository;
import rx.Observable;

public class FailureModeService {

    @Inject
    private FailureModeRepository repository;
    @Inject
    private HttpExecutionContext executionContext;
    @Inject
    private CycleAvoidingMappingContext mappingContext;
    @Inject
    private ActorSystem system;

    public Observable<FailureModeResource> findAll() {
        return repository.findAll().map(failureMode -> INSTANCE.toFailureModeResource(failureMode, mappingContext));
    }

    public CompletionStage<Optional<FailureModeResource>> findById(Long id) {
//        return repository.findById(id).thenApplyAsync(optionalFailureMode -> optionalFailureMode.map(convertToResource()),
//                executionContext.current());
        return null;
    }

    public CompletionStage<FailureModeResource> create(FailureModeResource resource) {
        repository
                .create(INSTANCE.toFailureMode(resource, mappingContext))
                .subscribe(success -> System.out.println("Insertion completed: " + resource));

        return CompletableFuture.supplyAsync(() -> resource);
    }

    public CompletionStage<Optional<FailureModeResource>> update(Long id, FailureModeResource resource) {
//        return repository.update(id, INSTANCE.toFailureMode(resource, mappingContext)).thenApplyAsync(optionalFailureMode ->
//                optionalFailureMode.map(convertToResource()));
        return null;
    }

//    public CompletionStage<FailureModeResource> addTag(Long id, Tag tag) {
//        return repository.addTag(id, tag).thenApplyAsync(convertToResource(), executionContext.current());
//    }

    private Function<FailureMode, FailureModeResource> convertToResource() {
        return failureMode -> INSTANCE.toFailureModeResource(failureMode, mappingContext);
    }
}
