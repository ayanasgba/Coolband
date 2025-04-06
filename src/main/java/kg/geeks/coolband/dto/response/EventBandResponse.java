package kg.geeks.coolband.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventBandResponse {

    private Long id;

    private String coverImage;

    private LocalDate date;

    private String name;

    private String bluer;

    public EventBandResponse(Long id, String coverImage, LocalDate date, String name, String bluer) {
        this.id = id;
        this.coverImage = coverImage;
        this.date = date;
        this.name = name;
        this.bluer = bluer;
    }
}
