package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.request.ContactsRequest;
import kg.geeks.coolband.dto.response.ContactsResponse;
import kg.geeks.coolband.entities.Contacts;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class ContactsMapperImpl implements ContactsMapper {

    @Override
    public Contacts mapRequestToResponse(ContactsRequest request) {
        if ( request == null ) {
            return null;
        }

        Contacts contacts = new Contacts();

        contacts.setLongitude( request.getLongitude() );
        contacts.setLatitude( request.getLatitude() );
        contacts.setCity( request.getCity() );
        contacts.setOrganizationId( request.getOrganizationId() );
        contacts.setAddress( request.getAddress() );
        contacts.setBandNumber( request.getBandNumber() );
        contacts.setStudioNumber( request.getStudioNumber() );
        contacts.setEmail( request.getEmail() );
        contacts.setTelegram( request.getTelegram() );
        contacts.setWhatsapp( request.getWhatsapp() );
        contacts.setInstagram( request.getInstagram() );
        contacts.setYoutube( request.getYoutube() );
        contacts.setTiktok( request.getTiktok() );

        return contacts;
    }

    @Override
    public ContactsResponse map(Contacts entity) {
        if ( entity == null ) {
            return null;
        }

        ContactsResponse.ContactsResponseBuilder contactsResponse = ContactsResponse.builder();

        contactsResponse.id( entity.getId() );
        contactsResponse.longitude( entity.getLongitude() );
        contactsResponse.latitude( entity.getLatitude() );
        contactsResponse.city( entity.getCity() );
        contactsResponse.organizationId( entity.getOrganizationId() );
        contactsResponse.address( entity.getAddress() );
        contactsResponse.bandNumber( entity.getBandNumber() );
        contactsResponse.studioNumber( entity.getStudioNumber() );
        contactsResponse.email( entity.getEmail() );
        contactsResponse.telegram( entity.getTelegram() );
        contactsResponse.whatsapp( entity.getWhatsapp() );
        contactsResponse.instagram( entity.getInstagram() );
        contactsResponse.youtube( entity.getYoutube() );
        contactsResponse.tiktok( entity.getTiktok() );

        return contactsResponse.build();
    }
}
