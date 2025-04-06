package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.response.HeroBandResponse;
import kg.geeks.coolband.entities.HeroBand;
import kg.geeks.coolband.enums.Gallery;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class HeroBandMapperImpl implements HeroBandMapper {

    @Override
    public HeroBandResponse map(HeroBand entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String video = null;
        Gallery orientation = null;

        id = entity.getId();
        video = entity.getVideo();
        orientation = entity.getOrientation();

        HeroBandResponse heroBandResponse = new HeroBandResponse( id, video, orientation );

        return heroBandResponse;
    }
}
