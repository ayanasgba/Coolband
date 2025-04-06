package kg.geeks.coolband.dto.request;

import kg.geeks.coolband.enums.Gallery;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AboutUsBandRequest{

    private MultipartFile image;

    private Gallery orientation;

    private String bluer;

}

