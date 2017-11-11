package services;

import model.database.FailureMode;
import model.database.Tag;
import model.presentation.FailureModeResource;
import play.libs.concurrent.HttpExecutionContext;
import repositories.FailureModeRepository;
import util.FailureModeUtil;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import static util.FailureModeUtil.updateFailureMode;

public class FailureModeService {

    private final FailureModeRepository repository;
    private final HttpExecutionContext executionContext;

    @Inject
    public FailureModeService(FailureModeRepository repository, HttpExecutionContext executionContext) {
        this.repository = repository;
        this.executionContext = executionContext;
    }

    public CompletionStage<Stream<FailureModeResource>> findAll() {
        return repository.list().thenApplyAsync(failureModeStream ->
                failureModeStream.map(FailureModeUtil::initializeFailureModeResource), executionContext.current());
    }

    public CompletionStage<Optional<FailureModeResource>> findById(Long id) {
        return repository.get(id).thenApplyAsync(optionalFailureMode ->
                optionalFailureMode.map(FailureModeUtil::initializeFailureModeResource), executionContext.current());
    }

    public CompletionStage<FailureMode> create(FailureModeResource resource) {
        return repository.create(createFailureMode(resource));
    }

    public CompletionStage<Optional<FailureMode>> update(Long id, FailureModeResource resource) {
        return repository.update(id, createFailureMode(resource));
    }

    public CompletionStage<FailureModeResource> addTag(Long id, Tag tag) {
        return repository.addTag(id, tag).thenApplyAsync(FailureModeUtil::initializeFailureModeResource, executionContext.current());
    }

    private FailureMode createFailureMode(FailureModeResource resource) {
        final FailureMode failureMode = new FailureMode();
        updateFailureMode(resource, failureMode);
        return failureMode;
    }
}
