package repositories;

import model.database.Tag;
import play.db.jpa.JPAApi;
import play.libs.concurrent.HttpExecutionContext;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class JPATagRepository implements TagRepository {
    private final JPAApi jpaApi;
    private final HttpExecutionContext executionContext;

    @Inject
    public JPATagRepository(JPAApi jpaApi, HttpExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Tag> create(Tag tag) {
        return supplyAsync(() -> jpaApi.withTransaction(em -> em.merge(tag)), executionContext.current());
    }

    @Override
    public CompletionStage<Tag> search(String text) {
        return null;
    }
}
