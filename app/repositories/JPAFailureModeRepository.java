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
        return null;
    }

    @Override
    public CompletionStage<Optional<FailureMode>> update(Long id, FailureMode failureMode) {
        return null;
    }

    private Stream<FailureMode> select(EntityManager em) {
        return em.createQuery("SELECT fm FROM FailureMode fm", FailureMode.class).getResultList().stream();
    }
}
