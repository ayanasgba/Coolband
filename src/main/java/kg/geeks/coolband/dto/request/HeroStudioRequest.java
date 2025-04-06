package kg.geeks.coolband.dto.request;

import kg.geeks.coolband.enums.Gallery;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class HeroStudioRequest {

    private String title;

    private String text;

    private MultipartFile video;

    private Gallery orientation;

    private boolean compress;

}
