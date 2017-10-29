package services;

import model.FailureMode;
import model.FailureModeResource;
import play.libs.concurrent.HttpExecutionContext;
import repositories.FailureModeRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public class FailureModeService {

    private final FailureModeRepository repository;
    private final HttpExecutionContext executionContext;

    @Inject
    public FailureModeService(FailureModeRepository repository, HttpExecutionContext executionContext) {
        this.repository = repository;
        this.executionContext = executionContext;
    }

    public CompletionStage<Stream<FailureModeResource>> findAll() {
        return repository.list().thenApplyAsync(FailureModeService::apply, executionContext.current());
    }

    public CompletionStage<FailureMode> create(FailureModeResource resource) {
        return repository.create(createFailureMode(resource));
    }

    private static Stream<FailureModeResource> apply(Stream<FailureMode> fms) {
        return fms.map(data -> {
            FailureModeResource resource = new FailureModeResource();
            resource.setDetectability(data.getDetectability());
            resource.setDetectFailures(data.getDetectFailures());
            resource.setFunctionalState(data.getFunctionalState());
            resource.setLastUpdated(data.getLastUpdated());
            resource.setMitigation(data.getMitigation());
            resource.setPlatformEffect(data.getPlatformEffect());
            resource.setPotentialCause(data.getPotentialCause());
            resource.setProbability(data.getProbability());
            resource.setResponseAction(data.getResponseAction());
            resource.setSafetyConcern(data.getSafetyConcern());
            resource.setServiceEffect(data.getServiceEffect());
            return resource;
        });
    }

    private FailureMode createFailureMode(FailureModeResource resource) {
        final FailureMode failureMode = new FailureMode();
        failureMode.setDetectability(resource.getDetectability());
        failureMode.setDetectFailures(resource.getDetectFailures());
        failureMode.setFunctionalState(resource.getFunctionalState());
        failureMode.setLastUpdated(resource.getLastUpdated());
        failureMode.setMitigation(resource.getMitigation());
        failureMode.setPlatformEffect(resource.getPlatformEffect());
        failureMode.setPotentialCause(resource.getPotentialCause());
        failureMode.setProbability(resource.getProbability());
        failureMode.setResponseAction(resource.getResponseAction());
        failureMode.setSafetyConcern(resource.getSafetyConcern());
        failureMode.setServiceEffect(resource.getServiceEffect());
        return failureMode;
    }
}
