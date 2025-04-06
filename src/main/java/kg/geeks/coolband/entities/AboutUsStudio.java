package kg.geeks.coolband.entities;

import jakarta.persistence.*;
import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "st_about_us")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AboutUsStudio extends BaseModel{

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "about_us_studio_gen")
    @SequenceGenerator(
            name = "about_us_studio_gen",
            sequenceName = "about_us_studio_seq",
            allocationSize = 1)
    Long id;

    String image;

    String imagePath;

    String youtubeUrl;

    String bluer;

    @Enumerated(EnumType.STRING)
    Gallery orientation;

}
