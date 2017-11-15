package repository;

import static java.util.Objects.nonNull;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static model.database.Tag.FIND_BY_TEXT;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import concurrent.FailureModeCatalogExecutionContext;
import model.database.Tag;
import play.db.jpa.JPAApi;

@Singleton
public class JPATagRepository implements TagRepository {

    @Inject
    private JPAApi jpaApi;
    @Inject
    private FailureModeCatalogExecutionContext executionContext;

    @Override
    public CompletionStage<Tag> create(Tag tag) {
        return supplyAsync(() -> jpaApi.withTransaction(em -> em.merge(tag)), executionContext);
    }

    @Override
    public CompletionStage<Optional<Tag>> search(String text) {
        return supplyAsync(() -> jpaApi.withTransaction(em -> selectById(em, text)), executionContext);
    }

    private Optional<Tag> selectById(EntityManager em, String text) {
        TypedQuery<Tag> typedQuery = em.createNamedQuery(FIND_BY_TEXT, Tag.class);
        typedQuery.setParameter("ptext", text);
        Tag singleResult = typedQuery.getSingleResult();
        return nonNull(singleResult) ? of(singleResult) : empty();
    }
}
