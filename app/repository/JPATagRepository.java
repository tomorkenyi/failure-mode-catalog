package repository;

import concurrent.FailureModeCatalogExecutionContext;
import model.database.Tag;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

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
    public CompletionStage<Tag> search(String text) {
        return null;
    }
}
