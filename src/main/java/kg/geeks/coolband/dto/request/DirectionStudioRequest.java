package kg.geeks.coolband.dto.request;

import jakarta.validation.constraints.NotNull;
import kg.geeks.coolband.enums.Gallery;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DirectionStudioRequest {

    @NotNull
    private String name;

    @NotNull
    private MultipartFile image;

    @NotNull
    private String bluer;

    @NotNull
    private Gallery orientation;

}
