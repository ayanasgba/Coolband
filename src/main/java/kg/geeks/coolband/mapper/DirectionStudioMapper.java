package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.request.DirectionStudioRequest;
import kg.geeks.coolband.dto.response.DirectionStudioResponse;
import kg.geeks.coolband.entities.DirectionStudio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DirectionStudioMapper {

    DirectionStudioMapper INSTANCE = Mappers.getMapper(DirectionStudioMapper.class);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    DirectionStudio mapRequestToResponse(DirectionStudioRequest request);

    DirectionStudioResponse map(DirectionStudio entity);

}
