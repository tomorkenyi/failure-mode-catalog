package mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import model.database.Tag;
import model.presentation.TagResource;

@Mapper
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagResource toTagResource(Tag tag, @Context CycleAvoidingMappingContext context);

    Tag toTag(TagResource tagResource, @Context CycleAvoidingMappingContext context);

}
