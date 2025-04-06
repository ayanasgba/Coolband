package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.request.EventBandImagesRequest;
import kg.geeks.coolband.dto.response.EventBandImagesResponse;
import kg.geeks.coolband.entities.EventBand;
import kg.geeks.coolband.entities.EventImagesBand;
import kg.geeks.coolband.entities.EventStudio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventImagesBandMapper {

    EventImagesBandMapper INSTANCE = Mappers.getMapper(EventImagesBandMapper.class);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "image",ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    @Mapping(target = "originalImage", ignore = true)
    @Mapping(target = "originalImagePath", ignore = true)
    @Mapping(target = "coverImage", source = "coverImage")
    EventImagesBand mapRequestToResponse(EventBandImagesRequest request);

    @Mapping(source = "coverImage",target = "isCoverImage")
    @Mapping(source = "originalImage",target = "originalImage")
    @Mapping(source = "eventBand.id", target = "albumId")
    EventBandImagesResponse map(EventImagesBand entity);


    default Long map(EventBand eventBand) {
        return eventBand != null ? eventBand.getId() : null;
    }

}
