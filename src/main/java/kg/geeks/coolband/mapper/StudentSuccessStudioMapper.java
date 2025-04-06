package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.request.StudentSuccessStudioRequest;
import kg.geeks.coolband.dto.response.StudentSuccessStudioResponse;
import kg.geeks.coolband.entities.StudentSuccessStudio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentSuccessStudioMapper {

    StudentSuccessStudioMapper INSTANCE = Mappers.getMapper(StudentSuccessStudioMapper.class);

    @Mapping(target = "id",ignore = true)
    StudentSuccessStudio mapRequestToResponse(StudentSuccessStudioRequest request);

    StudentSuccessStudioResponse map(StudentSuccessStudio entity);

}
