package kg.geeks.coolband.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "st_student_success")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentSuccessStudio extends BaseModel{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "st_student_success_gen")
    @SequenceGenerator(
            name = "st_student_success_gen",
            sequenceName = "st_student_success_seq",
            allocationSize = 1)
    Long id;

    @Column(length = 2200)
    String url;

}
