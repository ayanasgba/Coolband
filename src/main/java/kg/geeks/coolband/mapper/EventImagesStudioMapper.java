package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.request.EventStudioImagesRequest;
import kg.geeks.coolband.dto.response.EventStudioImagesResponse;
import kg.geeks.coolband.entities.EventImagesStudio;
import kg.geeks.coolband.entities.EventStudio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventImagesStudioMapper {

    EventImagesStudioMapper INSTANCE = Mappers.getMapper(EventImagesStudioMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    @Mapping(target = "originalImage", ignore = true)
    @Mapping(target = "originalImagePath", ignore = true)
    @Mapping(target = "isCoverImage", source = "coverImage")
    EventImagesStudio mapRequestToResponse(EventStudioImagesRequest request);

    @Mapping(source = "coverImage", target = "isCoverImage")
    @Mapping(source = "originalImage", target = "originalImage")
    @Mapping(source = "eventStudio.id", target = "albumId")
    EventStudioImagesResponse map(EventImagesStudio entity);

    default Long map(EventStudio eventStudio) {
        return eventStudio != null ? eventStudio.getId() : null;
    }

}
