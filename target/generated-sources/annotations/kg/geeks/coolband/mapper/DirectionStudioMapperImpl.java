package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.request.DirectionStudioRequest;
import kg.geeks.coolband.dto.response.DirectionStudioResponse;
import kg.geeks.coolband.entities.DirectionStudio;
import kg.geeks.coolband.enums.Gallery;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class DirectionStudioMapperImpl implements DirectionStudioMapper {

    @Override
    public DirectionStudio mapRequestToResponse(DirectionStudioRequest request) {
        if ( request == null ) {
            return null;
        }

        DirectionStudio directionStudio = new DirectionStudio();

        directionStudio.setName( request.getName() );
        directionStudio.setBluer( request.getBluer() );
        directionStudio.setOrientation( request.getOrientation() );

        return directionStudio;
    }

    @Override
    public DirectionStudioResponse map(DirectionStudio entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String image = null;
        String bluer = null;
        Gallery orientation = null;

        id = entity.getId();
        name = entity.getName();
        image = entity.getImage();
        bluer = entity.getBluer();
        orientation = entity.getOrientation();

        DirectionStudioResponse directionStudioResponse = new DirectionStudioResponse( id, name, image, bluer, orientation );

        return directionStudioResponse;
    }
}
