package repository;

import model.database.Tag;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class MongoTagRepository implements TagRepository {


    @Override
    public CompletionStage<Optional<Tag>> create(Long failureModeId, Tag tag) {
        return null;
    }

    @Override
    public CompletionStage<Optional<Tag>> search(String text) {
        return null;
    }

    @Override
    public CompletionStage<Void> delete(Long failureModeId, Long tagId) {
        return null;
    }
}
