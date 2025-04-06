package kg.geeks.coolband.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventBandRequest {

    @NotNull
    private LocalDate date;

    @NotNull
    private String name;

}
