package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.request.StTeachersRequest;
import kg.geeks.coolband.dto.response.StTeachersResponse;
import kg.geeks.coolband.entities.StTeachers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StTeachersMapper {

    StTeachersMapper INSTANCE = Mappers.getMapper(StTeachersMapper.class);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    StTeachers mapRequestToResponse(StTeachersRequest request);

    StTeachersResponse map(StTeachers entity);

}
