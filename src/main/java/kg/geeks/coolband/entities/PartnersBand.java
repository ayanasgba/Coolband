package kg.geeks.coolband.entities;

import jakarta.persistence.*;
import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="band_partners")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartnersBand extends BaseModel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "partners_gen")
    @SequenceGenerator(
            name = "partners_gen",
            sequenceName = "partners_seq",
            allocationSize = 1)
    Long id;

    String image;

    String imagePath;

    String bluer;

    String url;

}
