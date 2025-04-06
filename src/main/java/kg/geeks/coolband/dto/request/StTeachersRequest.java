package kg.geeks.coolband.dto.request;

import jakarta.validation.constraints.NotNull;
import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StTeachersRequest {

     @NotNull
     String name;

     @NotNull
     MultipartFile image;

     @NotNull
     String bluer;

     @NotNull
     String urlInstagram;

     @NotNull
     String position;

     @NotNull
     String description;

     @NotNull
     Gallery orientation;

}
