package kg.geeks.coolband.entities;

import jakarta.persistence.*;
import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "st_student_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StStudentReviews extends BaseModel{

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "st_student_reviews_gen")
    @SequenceGenerator(
            name = "st_student_reviews_gen",
            sequenceName = "st_student_reviews_seq",
            allocationSize = 1)
    Long id;

    String image;

    String imagePath;

    String bluer;

    String name;

    String reviews;

    @Enumerated(EnumType.STRING)
    Gallery orientation;

}
