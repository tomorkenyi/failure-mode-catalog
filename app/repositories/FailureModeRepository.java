package repositories;

import model.FailureMode;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface FailureModeRepository {

    CompletionStage<Stream<FailureMode>> list();

    CompletionStage<FailureMode> create(FailureMode failureMode);

    CompletionStage<Optional<FailureMode>> get(Long id);

    CompletionStage<Optional<FailureMode>> update(Long id, FailureMode failureMode);

}
