package kg.geeks.coolband.entities;

import jakarta.persistence.*;
import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="band_collaborations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollaborationsBand extends BaseModel{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "collaborations_gen")
    @SequenceGenerator(
            name = "collaborations_gen",
            sequenceName = "collaborations_seq",
            allocationSize = 1)
    Long id;

    String name;

    String image;

    String imagePath;

    String bluer;

    @Enumerated(EnumType.STRING)
    private Gallery orientation;
}
