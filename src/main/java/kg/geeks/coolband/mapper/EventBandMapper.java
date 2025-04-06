package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.request.EventBandRequest;
import kg.geeks.coolband.dto.response.EventBandResponse;
import kg.geeks.coolband.entities.EventBand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventBandMapper {

    EventBandMapper INSTANCE = Mappers.getMapper(EventBandMapper.class);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "coverImage",ignore = true)
    EventBand mapRequestToResponse(EventBandRequest request);

    EventBandResponse map(EventBand entity);

}
