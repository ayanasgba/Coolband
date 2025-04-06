package kg.geeks.coolband.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class EventStudioRequest {

    @NotNull
    private LocalDate date;

    @NotNull
    private String name;

    @NotNull
    private String location;

}
