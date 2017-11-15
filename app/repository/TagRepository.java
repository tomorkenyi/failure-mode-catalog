package repository;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

import model.database.Tag;

public interface TagRepository {

    CompletionStage<Tag> create(Tag tag);

    CompletionStage<Optional<Tag>> search(String text);

}
