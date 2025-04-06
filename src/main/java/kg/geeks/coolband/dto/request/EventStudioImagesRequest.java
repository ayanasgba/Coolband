package kg.geeks.coolband.dto.request;

import jakarta.validation.constraints.NotNull;
import kg.geeks.coolband.enums.Gallery;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class EventStudioImagesRequest {

    @NotNull
    private Long albumId;

    @NotNull
    private MultipartFile image;

    @NotNull
    private Gallery orientation;

    @NotNull
    private boolean isCoverImage;

    @NotNull
    private String bluer;


}
