package repository;

import model.database.Tag;

import java.util.concurrent.CompletionStage;

public interface TagRepository {

    CompletionStage<Tag> create(Tag tag);

    CompletionStage<Tag> search(String text);

}
