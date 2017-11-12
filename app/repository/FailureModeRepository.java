package repository;

import model.database.FailureMode;
import model.database.Tag;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface FailureModeRepository {

    CompletionStage<Stream<FailureMode>> findAll();

    CompletionStage<Optional<FailureMode>> findById(Long id);

    CompletionStage<FailureMode> create(FailureMode failureMode);

    CompletionStage<Optional<FailureMode>> update(Long id, FailureMode failureMode);

    CompletionStage<FailureMode> addTag(Long id, Tag tag);

}
