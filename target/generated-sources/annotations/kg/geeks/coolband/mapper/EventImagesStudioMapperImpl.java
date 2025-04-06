package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.request.EventStudioImagesRequest;
import kg.geeks.coolband.dto.response.EventStudioImagesResponse;
import kg.geeks.coolband.entities.EventImagesStudio;
import kg.geeks.coolband.entities.EventStudio;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class EventImagesStudioMapperImpl implements EventImagesStudioMapper {

    @Override
    public EventImagesStudio mapRequestToResponse(EventStudioImagesRequest request) {
        if ( request == null ) {
            return null;
        }

        EventImagesStudio.EventImagesStudioBuilder eventImagesStudio = EventImagesStudio.builder();

        eventImagesStudio.isCoverImage( request.isCoverImage() );
        eventImagesStudio.orientation( request.getOrientation() );
        eventImagesStudio.bluer( request.getBluer() );

        return eventImagesStudio.build();
    }

    @Override
    public EventStudioImagesResponse map(EventImagesStudio entity) {
        if ( entity == null ) {
            return null;
        }

        EventStudioImagesResponse.EventStudioImagesResponseBuilder eventStudioImagesResponse = EventStudioImagesResponse.builder();

        eventStudioImagesResponse.isCoverImage( entity.isCoverImage() );
        eventStudioImagesResponse.originalImage( entity.getOriginalImage() );
        eventStudioImagesResponse.albumId( entityEventStudioId( entity ) );
        eventStudioImagesResponse.id( entity.getId() );
        eventStudioImagesResponse.image( entity.getImage() );
        eventStudioImagesResponse.orientation( entity.getOrientation() );
        eventStudioImagesResponse.bluer( entity.getBluer() );

        return eventStudioImagesResponse.build();
    }

    private Long entityEventStudioId(EventImagesStudio eventImagesStudio) {
        if ( eventImagesStudio == null ) {
            return null;
        }
        EventStudio eventStudio = eventImagesStudio.getEventStudio();
        if ( eventStudio == null ) {
            return null;
        }
        Long id = eventStudio.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
