package kg.geeks.coolband.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class EventStudioResponse {

    private Long id;

    private LocalDate date;

    private String name;

    private String coverImage;

    private String bluer;

    private String location;

    public EventStudioResponse(Long id, LocalDate date, String name, String coverImage, String bluer, String location) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.coverImage = coverImage;
        this.bluer = bluer;
        this.location = location;
    }
}
