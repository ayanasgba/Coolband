package kg.geeks.coolband.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "st_event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventStudio {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "st_event_gen")
    @SequenceGenerator(
            name = "st_event_gen",
            sequenceName = "st_event_seq",
            allocationSize = 1)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String name;

    private String coverImage;

    private String location;

    private String bluer;

    @OneToMany(mappedBy = "eventStudio", cascade = CascadeType.REFRESH)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private List<EventImagesStudio> eventImagesStudio;
}