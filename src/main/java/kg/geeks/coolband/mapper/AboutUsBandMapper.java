package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.request.AboutUsBandRequest;
import kg.geeks.coolband.dto.response.AboutUsBandResponse;
import kg.geeks.coolband.entities.AboutUsBand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AboutUsBandMapper {

    AboutUsBandMapper INSTANCE = Mappers.getMapper(AboutUsBandMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    AboutUsBand mapRequestToResponse(AboutUsBandRequest request);

    AboutUsBandResponse map(AboutUsBand entity);

}
