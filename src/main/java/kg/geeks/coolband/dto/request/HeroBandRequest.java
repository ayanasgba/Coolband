package kg.geeks.coolband.dto.request;

import kg.geeks.coolband.enums.Gallery;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HeroBandRequest {

    MultipartFile video;

    Gallery orientation;

    boolean compress;

}


