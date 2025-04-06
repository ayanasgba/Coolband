package kg.geeks.coolband.entities;

import jakarta.persistence.*;
import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="band_team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamBand extends BaseModel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "team_band_gen")
    @SequenceGenerator(
            name = "team_band_gen",
            sequenceName = "team_band_seq",
            allocationSize = 1)
    Long id;

    String name;

    String image;

    String imagePath;

    String video;

    String videoPath;

    String instrument;

    String bluer;

    @Enumerated(EnumType.STRING)
    Gallery orientation;

}
