package kg.geeks.coolband.dto.request;

import jakarta.validation.constraints.NotNull;
import kg.geeks.coolband.enums.Gallery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class StStudentReviewsRequest {

    @NotNull
    private MultipartFile image;

    @NotNull
    private String bluer;

    @NotNull
    private String name;

    @NotNull
    private String reviews;

    @NotNull
    private Gallery orientation;

}
