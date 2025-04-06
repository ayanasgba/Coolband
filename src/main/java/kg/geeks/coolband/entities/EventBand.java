package kg.geeks.coolband.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "band_event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventBand extends BaseModel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "band_event_gen")
    @SequenceGenerator(
            name = "band_event_gen",
            sequenceName = "band_event_seq",
            allocationSize = 1)
    Long id;

    String coverImage;

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;

    String name;

    String bluer;

    @OneToMany(mappedBy = "eventBand", cascade = CascadeType.REFRESH)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private List<EventImagesBand> eventImagesBand;


}
