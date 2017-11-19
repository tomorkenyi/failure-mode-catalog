package repository;

import concurrent.FailureModeCatalogExecutionContext;
import model.database.FailureMode;
import model.database.Tag;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static model.database.FailureMode.FIND_ALL;

@Singleton
public class JPAFailureModeRepository implements FailureModeRepository {

    @Inject
    private JPAApi jpaApi;
    @Inject
    private FailureModeCatalogExecutionContext executionContext;

    @Override
    public CompletionStage<Stream<FailureMode>> findAll() {
        return supplyAsync(() -> jpaApi.withTransaction(this::selectAll), executionContext);
    }

    @Override
    public CompletionStage<Optional<FailureMode>> findById(Long id) {
        return supplyAsync(() -> jpaApi.withTransaction(em -> of(em.find(FailureMode.class, id))), executionContext);
    }

    @Override
    public CompletionStage<FailureMode> create(FailureMode failureMode) {
        return supplyAsync(() -> jpaApi.withTransaction(em -> em.merge(failureMode)), executionContext);
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
                data.setTags(failureMode.getTags());
            }
            return ofNullable(data);
        }));
    }

    @Override
    public CompletionStage<FailureMode> addTag(Long id, Tag tag) {
        return supplyAsync(() -> jpaApi.withTransaction(em -> {
            FailureMode failureMode = em.find(FailureMode.class, id);
            if (failureMode != null) {
                Set<Tag> tags = failureMode.getTags();
                tags.add(tag);
                failureMode.setTags(tags);
                em.merge(failureMode);
            }
            return failureMode;
        }));
    }

    private Stream<FailureMode> selectAll(EntityManager em) {
        return em.createNamedQuery(FIND_ALL, FailureMode.class).getResultList().stream();
    }
}
