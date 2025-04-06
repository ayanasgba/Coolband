package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.request.TeamBandRequest;
import kg.geeks.coolband.dto.response.TeamBandResponse;
import kg.geeks.coolband.entities.TeamBand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeamBandMapper {

    TeamBandMapper INSTANCE = Mappers.getMapper(TeamBandMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "video", ignore = true)
    @Mapping(target = "videoPath", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    TeamBand mapRequestToResponse(TeamBandRequest request);

    TeamBandResponse map(TeamBand entity);

}
