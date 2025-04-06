package kg.geeks.coolband.dto.response;

import kg.geeks.coolband.enums.Gallery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AboutUsBandResponse {

    private Long id;

    private String image;

    private Gallery orientation;

    private String bluer;

    public AboutUsBandResponse(Long id, String image, Gallery orientation, String bluer) {
        this.id = id;
        this.image = image;
        this.orientation = orientation;
        this.bluer = bluer;
    }
}
