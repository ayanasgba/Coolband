package kg.geeks.coolband.entities;


import jakarta.persistence.*;
import kg.geeks.coolband.enums.Gallery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "band_event_images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventImagesBand extends BaseModel{

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "band_event_images_gen")
    @SequenceGenerator(
            name = "band_event_images_gen",
            sequenceName = "band_event_images_seq",
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
    private EventBand eventBand;
}
