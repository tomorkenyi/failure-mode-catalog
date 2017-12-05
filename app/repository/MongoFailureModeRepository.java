package repository;

import static conf.MongoConfig.db;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

import org.bson.Document;

import com.mongodb.rx.client.MongoCollection;
import com.mongodb.rx.client.Success;

import model.database.FailureMode;
import model.database.Tag;
import rx.Observable;
import util.FailureModeUtil;

public class MongoFailureModeRepository implements FailureModeRepository {

    private static final String FAILUREMODES_COLLECTION_NAME = "failuremodes";

    private final MongoCollection<Document> fmCollection;

    public MongoFailureModeRepository() {
        this.fmCollection = db.getCollection(FAILUREMODES_COLLECTION_NAME);
    }

    @Override
    public Observable<FailureMode> findAll() {
        return fmCollection
                .find()
                .toObservable()
                .doOnNext(System.out::println)
                .map(FailureModeUtil::convertToFailureMode);
    }

    @Override
    public CompletionStage<Optional<FailureMode>> findById(Long id) {
        return null;
    }

    @Override
    public Observable<Success> create(FailureMode failureMode) {
        return fmCollection.insertOne(new Document("functionalState", failureMode.getFunctionalState()));
    }

    @Override
    public CompletionStage<Optional<FailureMode>> update(Long id, FailureMode failureMode) {
        return null;
    }

    @Override
    public CompletionStage<FailureMode> addTag(Long id, Tag tag) {
        return null;
    }

}
