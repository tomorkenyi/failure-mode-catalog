package service;

import model.database.FailureMode;
import model.database.Tag;
import model.presentation.FailureModeResource;
import play.libs.concurrent.HttpExecutionContext;
import repository.FailureModeRepository;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static mapper.FailureModeMapper.INSTANCE;

public class FailureModeService {

    @Inject
    private FailureModeRepository repository;
    @Inject
    private HttpExecutionContext executionContext;

    public CompletionStage<Stream<FailureModeResource>> findAll() {
        return repository.findAll().thenApplyAsync(failureModeStream -> failureModeStream.map(convertToResource()),
                executionContext.current());
    }

    public CompletionStage<Optional<FailureModeResource>> findById(Long id) {
        return repository.findById(id).thenApplyAsync(optionalFailureMode -> optionalFailureMode.map(convertToResource()),
                executionContext.current());
    }

    public CompletionStage<FailureModeResource> create(FailureModeResource resource) {
        return repository.create(INSTANCE.failureModeResourceToEntity(resource)).thenApplyAsync(convertToResource());
    }

    public CompletionStage<Optional<FailureModeResource>> update(Long id, FailureModeResource resource) {
        return repository.update(id, INSTANCE.failureModeResourceToEntity(resource)).thenApplyAsync(optionalFailureMode ->
                optionalFailureMode.map(convertToResource()));
    }

    public CompletionStage<FailureModeResource> addTag(Long id, Tag tag) {
        return repository.addTag(id, tag).thenApplyAsync(convertToResource(), executionContext.current());
    }

    private Function<FailureMode, FailureModeResource> convertToResource() {
        return failureMode -> INSTANCE.failureModeEntityToResource(failureMode);
    }

}
