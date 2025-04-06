package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.request.EventStudioRequest;
import kg.geeks.coolband.dto.response.EventStudioResponse;
import kg.geeks.coolband.entities.EventStudio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventStudioMapper {

    EventStudioMapper INSTANCE = Mappers.getMapper(EventStudioMapper.class);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "coverImage",ignore = true)
    EventStudio mapRequestToResponse(EventStudioRequest request);

    EventStudioResponse map(EventStudio entity);

}
