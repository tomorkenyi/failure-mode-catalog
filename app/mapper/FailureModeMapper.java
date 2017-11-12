package mapper;

import model.database.FailureMode;
import model.presentation.FailureModeResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FailureModeMapper {

    FailureModeMapper INSTANCE = Mappers.getMapper(FailureModeMapper.class);

    FailureModeResource failureModeEntityToResource(FailureMode failureMode);

}
