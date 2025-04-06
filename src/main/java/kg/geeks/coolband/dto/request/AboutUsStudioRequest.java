package kg.geeks.coolband.dto.request;

import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AboutUsStudioRequest {

    private MultipartFile image;

    private Gallery orientation;

    private String youtubeUrl;

    private String bluer;

}
