package kg.geeks.coolband.entities;

import jakarta.persistence.*;
import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="band_about_us")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutUsBand extends BaseModel{

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "about_us_band_gen")
    @SequenceGenerator(
            name = "about_us_band_gen",
            sequenceName = "about_us_band_seq",
            allocationSize = 1)
    Long id;

    String image;

    String imagePath;

    String bluer;

    @Enumerated(EnumType.STRING)
    Gallery orientation;


}
