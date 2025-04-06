package kg.geeks.coolband.entities;

import jakarta.persistence.*;
import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "st_direction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DirectionStudio extends BaseModel{

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "direction_gen")
    @SequenceGenerator(
            name = "direction_gen",
            sequenceName = "direction_seq",
            allocationSize = 1)
    Long id;

    String name;

    String image;

    String imagePath;

    String bluer;

    @Enumerated(EnumType.STRING)
    Gallery orientation;

}
