package kg.geeks.coolband.entities;


import jakarta.persistence.*;
import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "band_hero")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HeroBand extends BaseModel{

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "band_hero_gen")
    @SequenceGenerator(
            name = "band_hero_gen",
            sequenceName = "band_hero_seq",
            allocationSize = 1)
    Long id;

    @Column(name = "video")
    String video;

    String videoPath;

    @Enumerated(EnumType.STRING)
    Gallery orientation;

}
