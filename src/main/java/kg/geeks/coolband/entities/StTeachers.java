package kg.geeks.coolband.entities;

import jakarta.persistence.*;
import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "st_teachers")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StTeachers extends BaseModel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "st_teachers_gen")
    @SequenceGenerator(
            name = "st_teachers_gen",
            sequenceName = "st_teachers_seq",
            allocationSize = 1)
    Long id;

    String name;

    String image;

    String imagePath;

    String bluer;

    String urlInstagram;

    String position;

    @Column(length = 2500)
    String description;

    @Enumerated(EnumType.STRING)
    Gallery orientation;

}
