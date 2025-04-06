package kg.geeks.coolband.entities;

import kg.geeks.coolband.enums.Gallery;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table  (name = "st_event_images")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventImagesStudio extends BaseModel{

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "st_event_images_gen")
    @SequenceGenerator(
            name = "st_event_images_gen",
            sequenceName = "st_event_images_seq",
            allocationSize = 1)
    private Long id;

    private String image;

    private String imagePath;

    private String originalImage;

    private String originalImagePath;

    @Enumerated(EnumType.STRING)
    private Gallery orientation;

    private boolean isCoverImage;

    private String bluer;

    @ManyToOne
    private EventStudio eventStudio;

}
