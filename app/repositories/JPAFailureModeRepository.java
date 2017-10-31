package repositories;

import model.FailureMode;
import play.db.jpa.JPAApi;
import play.libs.concurrent.HttpExecutionContext;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;
import static java.util.Optional.*;
import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class JPAFailureModeRepository implements FailureModeRepository {

    private final JPAApi jpaApi;
    private final HttpExecutionContext executionContext;

    @Inject
    public JPAFailureModeRepository(JPAApi jpaApi, HttpExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Stream<FailureMode>> list() {
        return supplyAsync(() -> jpaApi.withTransaction(this::select), executionContext.current());
    }

    @Override
    public CompletionStage<FailureMode> create(FailureMode failureMode) {
        return supplyAsync(() -> jpaApi.withTransaction(em -> em.merge(failureMode)), executionContext.current());
    }

    @Override
    public CompletionStage<Optional<FailureMode>> get(Long id) {
        return supplyAsync(() -> jpaApi.withTransaction(em -> {
            FailureMode value = em.find(FailureMode.class, id);
            return nonNull(value) ? of(value) : empty();
        }), executionContext.current());
    }

    @Override
    public CompletionStage<Optional<FailureMode>> update(Long id, FailureMode failureMode) {
        return supplyAsync(() -> jpaApi.withTransaction(em -> {
            final FailureMode data = em.find(FailureMode.class, id);
            if (data != null) {
                data.setDetectability(failureMode.getDetectability());
                data.setDetectFailures(failureMode.getDetectFailures());
                data.setFunctionalState(failureMode.getFunctionalState());
                data.setLastUpdated(failureMode.getLastUpdated());
                data.setMitigation(failureMode.getMitigation());
                data.setPlatformEffect(failureMode.getPlatformEffect());
                data.setPotentialCause(failureMode.getPotentialCause());
                data.setProbability(failureMode.getProbability());
                data.setResponseAction(failureMode.getResponseAction());
                data.setSafetyConcern(failureMode.getSafetyConcern());
                data.setServiceEffect(failureMode.getServiceEffect());
            }
            return ofNullable(data);
        }));
    }

    private Stream<FailureMode> select(EntityManager em) {
        return em.createQuery("SELECT fm FROM FailureMode fm", FailureMode.class).getResultList().stream();
    }
}
