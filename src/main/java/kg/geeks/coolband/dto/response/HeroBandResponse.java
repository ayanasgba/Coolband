package kg.geeks.coolband.dto.response;

import kg.geeks.coolband.enums.Gallery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HeroBandResponse {

    Long id;

    String video;

    Gallery orientation;

}
