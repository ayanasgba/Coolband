package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.request.CollaborationsBandRequest;
import kg.geeks.coolband.dto.response.CollaborationsBandResponse;
import kg.geeks.coolband.entities.CollaborationsBand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CollaborationsBandMapper {

    CollaborationsBandMapper INSTANCE = Mappers.getMapper(CollaborationsBandMapper.class);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    CollaborationsBand mapRequestToResponse(CollaborationsBandRequest request);

    CollaborationsBandResponse map(CollaborationsBand entity);

}
