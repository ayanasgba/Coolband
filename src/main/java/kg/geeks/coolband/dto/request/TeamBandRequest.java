package kg.geeks.coolband.dto.request;

import jakarta.validation.constraints.NotNull;
import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class TeamBandRequest {

    @NotNull
    private String name;

    @NotNull
    private MultipartFile image;

    @NotNull
    private MultipartFile video;

    @NotNull
    private String bluer;

    @NotNull
    private String instrument;

    @NotNull
    private Gallery orientation;

    @NotNull
    private boolean compress;


}
