package kg.geeks.coolband.dto.response;

import kg.geeks.coolband.enums.Gallery;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class StTeachersResponse {

     Long id;

     String name;

     String image;

     String bluer;

     String urlInstagram;

     String position;

     String description;

     Gallery orientation;

}