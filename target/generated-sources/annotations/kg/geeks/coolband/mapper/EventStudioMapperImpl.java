package kg.geeks.coolband.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.request.EventStudioRequest;
import kg.geeks.coolband.dto.response.EventStudioResponse;
import kg.geeks.coolband.entities.EventStudio;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class EventStudioMapperImpl implements EventStudioMapper {

    @Override
    public EventStudio mapRequestToResponse(EventStudioRequest request) {
        if ( request == null ) {
            return null;
        }

        EventStudio eventStudio = new EventStudio();

        eventStudio.setDate( request.getDate() );
        eventStudio.setName( request.getName() );
        eventStudio.setLocation( request.getLocation() );

        return eventStudio;
    }

    @Override
    public EventStudioResponse map(EventStudio entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        LocalDate date = null;
        String name = null;
        String coverImage = null;
        String bluer = null;
        String location = null;

        id = entity.getId();
        date = entity.getDate();
        name = entity.getName();
        coverImage = entity.getCoverImage();
        bluer = entity.getBluer();
        location = entity.getLocation();

        EventStudioResponse eventStudioResponse = new EventStudioResponse( id, date, name, coverImage, bluer, location );

        return eventStudioResponse;
    }
}
