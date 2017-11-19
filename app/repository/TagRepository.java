package repository;

import model.database.Tag;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface TagRepository {

    CompletionStage<Optional<Tag>> create(Long failureModeId, Tag tag);

    CompletionStage<Optional<Tag>> search(String text);

    CompletionStage<Void> delete(Long failureModeId, Long tagId);
}
