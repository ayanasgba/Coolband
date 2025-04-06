package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.request.AboutUsBandRequest;
import kg.geeks.coolband.dto.response.AboutUsBandResponse;
import kg.geeks.coolband.entities.AboutUsBand;
import kg.geeks.coolband.enums.Gallery;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class AboutUsBandMapperImpl implements AboutUsBandMapper {

    @Override
    public AboutUsBand mapRequestToResponse(AboutUsBandRequest request) {
        if ( request == null ) {
            return null;
        }

        AboutUsBand aboutUsBand = new AboutUsBand();

        aboutUsBand.setBluer( request.getBluer() );
        aboutUsBand.setOrientation( request.getOrientation() );

        return aboutUsBand;
    }

    @Override
    public AboutUsBandResponse map(AboutUsBand entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String image = null;
        Gallery orientation = null;
        String bluer = null;

        id = entity.getId();
        image = entity.getImage();
        orientation = entity.getOrientation();
        bluer = entity.getBluer();

        AboutUsBandResponse aboutUsBandResponse = new AboutUsBandResponse( id, image, orientation, bluer );

        return aboutUsBandResponse;
    }
}
