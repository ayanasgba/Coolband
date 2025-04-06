package kg.geeks.coolband.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.request.EventBandRequest;
import kg.geeks.coolband.dto.response.EventBandResponse;
import kg.geeks.coolband.entities.EventBand;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class EventBandMapperImpl implements EventBandMapper {

    @Override
    public EventBand mapRequestToResponse(EventBandRequest request) {
        if ( request == null ) {
            return null;
        }

        EventBand eventBand = new EventBand();

        eventBand.setDate( request.getDate() );
        eventBand.setName( request.getName() );

        return eventBand;
    }

    @Override
    public EventBandResponse map(EventBand entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String coverImage = null;
        LocalDate date = null;
        String name = null;
        String bluer = null;

        id = entity.getId();
        coverImage = entity.getCoverImage();
        date = entity.getDate();
        name = entity.getName();
        bluer = entity.getBluer();

        EventBandResponse eventBandResponse = new EventBandResponse( id, coverImage, date, name, bluer );

        return eventBandResponse;
    }
}
