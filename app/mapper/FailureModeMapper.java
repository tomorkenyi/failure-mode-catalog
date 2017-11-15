package mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import model.database.FailureMode;
import model.presentation.FailureModeResource;

@Mapper
public interface FailureModeMapper {

    FailureModeMapper INSTANCE = Mappers.getMapper(FailureModeMapper.class);

    FailureModeResource toFailureModeResource(FailureMode failureMode, @Context CycleAvoidingMappingContext context);

    FailureMode toFailureMode(FailureModeResource failureModeResource, @Context CycleAvoidingMappingContext context);

}
