package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.request.PartnersBandRequest;
import kg.geeks.coolband.dto.response.PartnersBandResponse;
import kg.geeks.coolband.entities.PartnersBand;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class PartnersBandMapperImpl implements PartnersBandMapper {

    @Override
    public PartnersBand mapRequestToResponse(PartnersBandRequest request) {
        if ( request == null ) {
            return null;
        }

        PartnersBand partnersBand = new PartnersBand();

        partnersBand.setBluer( request.getBluer() );
        partnersBand.setUrl( request.getUrl() );

        return partnersBand;
    }

    @Override
    public PartnersBandResponse map(PartnersBand entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String image = null;
        String bluer = null;
        String url = null;

        id = entity.getId();
        image = entity.getImage();
        bluer = entity.getBluer();
        url = entity.getUrl();

        PartnersBandResponse partnersBandResponse = new PartnersBandResponse( id, image, bluer, url );

        return partnersBandResponse;
    }
}
