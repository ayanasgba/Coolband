package kg.geeks.coolband.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSuccessStudioRequest {

    @NotNull
    private String url;

}
