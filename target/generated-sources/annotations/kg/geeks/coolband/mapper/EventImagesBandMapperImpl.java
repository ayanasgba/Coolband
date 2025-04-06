package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.request.EventBandImagesRequest;
import kg.geeks.coolband.dto.response.EventBandImagesResponse;
import kg.geeks.coolband.entities.EventBand;
import kg.geeks.coolband.entities.EventImagesBand;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class EventImagesBandMapperImpl implements EventImagesBandMapper {

    @Override
    public EventImagesBand mapRequestToResponse(EventBandImagesRequest request) {
        if ( request == null ) {
            return null;
        }

        EventImagesBand eventImagesBand = new EventImagesBand();

        eventImagesBand.setCoverImage( request.isCoverImage() );
        eventImagesBand.setOrientation( request.getOrientation() );
        eventImagesBand.setBluer( request.getBluer() );

        return eventImagesBand;
    }

    @Override
    public EventBandImagesResponse map(EventImagesBand entity) {
        if ( entity == null ) {
            return null;
        }

        EventBandImagesResponse.EventBandImagesResponseBuilder eventBandImagesResponse = EventBandImagesResponse.builder();

        eventBandImagesResponse.isCoverImage( entity.isCoverImage() );
        eventBandImagesResponse.originalImage( entity.getOriginalImage() );
        eventBandImagesResponse.albumId( entityEventBandId( entity ) );
        eventBandImagesResponse.id( entity.getId() );
        eventBandImagesResponse.image( entity.getImage() );
        eventBandImagesResponse.orientation( entity.getOrientation() );
        eventBandImagesResponse.bluer( entity.getBluer() );

        return eventBandImagesResponse.build();
    }

    private Long entityEventBandId(EventImagesBand eventImagesBand) {
        if ( eventImagesBand == null ) {
            return null;
        }
        EventBand eventBand = eventImagesBand.getEventBand();
        if ( eventBand == null ) {
            return null;
        }
        Long id = eventBand.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
