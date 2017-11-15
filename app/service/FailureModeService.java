package service;

import static mapper.FailureModeMapper.INSTANCE;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.inject.Inject;

import mapper.CycleAvoidingMappingContext;
import model.database.FailureMode;
import model.database.Tag;
import model.presentation.FailureModeResource;
import play.libs.concurrent.HttpExecutionContext;
import repository.FailureModeRepository;

public class FailureModeService {

    @Inject
    private FailureModeRepository repository;
    @Inject
    private HttpExecutionContext executionContext;
    @Inject
    private CycleAvoidingMappingContext mappingContext;

    public CompletionStage<Stream<FailureModeResource>> findAll() {
        return repository.findAll().thenApplyAsync(failureModeStream -> failureModeStream.map(convertToResource()),
                executionContext.current());
    }

    public CompletionStage<Optional<FailureModeResource>> findById(Long id) {
        return repository.findById(id).thenApplyAsync(optionalFailureMode -> optionalFailureMode.map(convertToResource()),
                executionContext.current());
    }

    public CompletionStage<FailureModeResource> create(FailureModeResource resource) {
        return repository.create(INSTANCE.toFailureMode(resource, mappingContext)).thenApplyAsync(convertToResource());
    }

    public CompletionStage<Optional<FailureModeResource>> update(Long id, FailureModeResource resource) {
        return repository.update(id, INSTANCE.toFailureMode(resource, mappingContext)).thenApplyAsync(optionalFailureMode ->
                optionalFailureMode.map(convertToResource()));
    }

    public CompletionStage<FailureModeResource> addTag(Long id, Tag tag) {
        return repository.addTag(id, tag).thenApplyAsync(convertToResource(), executionContext.current());
    }

    private Function<FailureMode, FailureModeResource> convertToResource() {
        return failureMode -> INSTANCE.toFailureModeResource(failureMode, mappingContext);
    }

}
