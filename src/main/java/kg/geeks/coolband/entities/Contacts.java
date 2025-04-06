package kg.geeks.coolband.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contacts extends BaseModel{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contacts_gen")
    @SequenceGenerator(
            name = "contacts_gen",
            sequenceName = "contacts_seq",
            allocationSize = 1)
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
}
