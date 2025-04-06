package kg.geeks.coolband.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactsResponse {

    Long id;

    String longitude;

    String latitude;

    String city;

    String organizationId;

    String address;

    String bandNumber;

    String studioNumber;

    String email;

    String telegram;

    String whatsapp;

    String instagram;

    String youtube;

    String tiktok;

    public ContactsResponse(Long id, String longitude, String latitude, String city, String organizationId, String address, String bandNumber, String studioNumber, String email, String telegram, String whatsapp, String instagram, String youtube, String tiktok) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.organizationId = organizationId;
        this.address = address;
        this.bandNumber = bandNumber;
        this.studioNumber = studioNumber;
        this.email = email;
        this.telegram = telegram;
        this.whatsapp = whatsapp;
        this.instagram = instagram;
        this.youtube = youtube;
        this.tiktok = tiktok;
    }
}
