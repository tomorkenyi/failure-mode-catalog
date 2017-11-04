package repositories;

import model.database.FailureMode;
import model.database.Tag;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface FailureModeRepository {

    CompletionStage<Stream<FailureMode>> list();

    CompletionStage<FailureMode> create(FailureMode failureMode);

    CompletionStage<Optional<FailureMode>> get(Long id);

    CompletionStage<Optional<FailureMode>> update(Long id, FailureMode failureMode);

    CompletionStage<FailureMode> addTag(FailureMode failureMode, Tag tag);

}
