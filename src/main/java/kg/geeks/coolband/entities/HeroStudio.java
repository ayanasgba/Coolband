package kg.geeks.coolband.entities;

import jakarta.persistence.*;
import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "st_hero")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HeroStudio extends BaseModel{

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "studio_hero_gen")
    @SequenceGenerator(
            name = "studio_hero_gen",
            sequenceName = "studio_hero_seq",
            allocationSize = 1)
    Long id;

    String title;

    String text;

    String video;

    String videoPath;

    @Enumerated(EnumType.STRING)
    Gallery orientation;

}
