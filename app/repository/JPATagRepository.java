package repository;

//
//import concurrent.FailureModeCatalogExecutionContext;
//import model.database.FailureMode;
//import model.database.Tag;
//import play.db.jpa.JPAApi;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityNotFoundException;
//import javax.persistence.TypedQuery;
//import java.util.Optional;
//import java.util.Set;
//import java.util.concurrent.CompletionStage;
//
//import static java.util.Objects.isNull;
//import static java.util.Objects.nonNull;
//import static java.util.Optional.empty;
//import static java.util.Optional.of;
//import static java.util.concurrent.CompletableFuture.supplyAsync;
//import static model.database.Tag.FIND_BY_TEXT;
//
//@Singleton
public class JPATagRepository /*implements TagRepository*/ {
//
//    @Inject
//    private JPAApi jpaApi;
//    @Inject
//    private FailureModeCatalogExecutionContext executionContext;
//
//    @Override
//    public CompletionStage<Optional<Tag>> create(Long failureModeId, Tag tag) {
//        return supplyAsync(() -> jpaApi.withTransaction(em -> {
//            FailureMode failureMode = em.find(FailureMode.class, failureModeId);
//            return addTag(tag, em, failureMode);
//        }), executionContext);
//    }
//
//    @Override
//    public CompletionStage<Optional<Tag>> search(String text) {
//        return supplyAsync(() -> jpaApi.withTransaction(em -> selectByTagText(em, text)), executionContext);
//    }
//
//    @Override
//    public CompletionStage<Void> delete(Long failureModeId, Long tagId) {
//        return supplyAsync(() -> jpaApi.withTransaction(em -> {
//            removeTag(failureModeId, tagId, em);
//            return null;
//        }));
//    }
//
//    private Optional<Tag> selectByTagText(EntityManager em, String text) {
//        TypedQuery<Tag> typedQuery = em.createNamedQuery(FIND_BY_TEXT, Tag.class);
//        typedQuery.setParameter("ptext", text);
//        Tag singleResult = typedQuery.getSingleResult();
//        return nonNull(singleResult) ? of(singleResult) : empty();
//    }
//
//    private Optional<Tag> addTag(Tag tag, EntityManager em, FailureMode failureMode) {
//        if (isNull(failureMode)) {
//            return empty();
//        } else {
//            Set<Tag> tags = failureMode.getTags();
//            tags.add(tag);
//            failureMode.setTags(tags);
//            em.merge(failureMode);
//
//            return of(tag);
//        }
//    }
//
//    private void removeTag(Long failureModeId, Long tagId, EntityManager em) {
//        FailureMode failureMode = em.find(FailureMode.class, failureModeId);
//        Tag tag = em.find(Tag.class, tagId);
//
//        if (isNull(failureMode) || isNull(tag)) {
//            throw new EntityNotFoundException(
//                    "Tag with id: " + tagId + " cannot be removed from failure mode: " + failureModeId);
//        } else {
//            final Set<Tag> tags = failureMode.getTags();
//            tags.remove(tag);
//            failureMode.setTags(tags);
//            em.merge(failureMode);
//        }
//    }
}
