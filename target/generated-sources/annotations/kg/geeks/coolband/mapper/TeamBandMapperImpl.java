package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.request.TeamBandRequest;
import kg.geeks.coolband.dto.response.TeamBandResponse;
import kg.geeks.coolband.entities.TeamBand;
import kg.geeks.coolband.enums.Gallery;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class TeamBandMapperImpl implements TeamBandMapper {

    @Override
    public TeamBand mapRequestToResponse(TeamBandRequest request) {
        if ( request == null ) {
            return null;
        }

        TeamBand teamBand = new TeamBand();

        teamBand.setName( request.getName() );
        teamBand.setInstrument( request.getInstrument() );
        teamBand.setBluer( request.getBluer() );
        teamBand.setOrientation( request.getOrientation() );

        return teamBand;
    }

    @Override
    public TeamBandResponse map(TeamBand entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String image = null;
        String video = null;
        String instrument = null;
        Gallery orientation = null;
        String bluer = null;

        id = entity.getId();
        name = entity.getName();
        image = entity.getImage();
        video = entity.getVideo();
        instrument = entity.getInstrument();
        orientation = entity.getOrientation();
        bluer = entity.getBluer();

        TeamBandResponse teamBandResponse = new TeamBandResponse( id, name, image, video, instrument, orientation, bluer );

        return teamBandResponse;
    }
}
