package repository;

import com.mongodb.rx.client.Success;
import model.database.FailureMode;
import model.database.Tag;
import rx.Observable;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface FailureModeRepository {

    Observable<FailureMode> findAll();

    CompletionStage<Optional<FailureMode>> findById(Long id);

    Observable<Success> create(FailureMode failureMode);

    CompletionStage<Optional<FailureMode>> update(Long id, FailureMode failureMode);

    CompletionStage<FailureMode> addTag(Long id, Tag tag);

}
