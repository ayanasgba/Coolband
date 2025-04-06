package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.request.CollaborationsBandRequest;
import kg.geeks.coolband.dto.response.CollaborationsBandResponse;
import kg.geeks.coolband.entities.CollaborationsBand;
import kg.geeks.coolband.enums.Gallery;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class CollaborationsBandMapperImpl implements CollaborationsBandMapper {

    @Override
    public CollaborationsBand mapRequestToResponse(CollaborationsBandRequest request) {
        if ( request == null ) {
            return null;
        }

        CollaborationsBand collaborationsBand = new CollaborationsBand();

        collaborationsBand.setName( request.getName() );
        collaborationsBand.setBluer( request.getBluer() );
        collaborationsBand.setOrientation( request.getOrientation() );

        return collaborationsBand;
    }

    @Override
    public CollaborationsBandResponse map(CollaborationsBand entity) {
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

        CollaborationsBandResponse collaborationsBandResponse = new CollaborationsBandResponse( id, name, image, bluer, orientation );

        return collaborationsBandResponse;
    }
}
