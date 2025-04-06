package kg.geeks.coolband.dto.response;

import kg.geeks.coolband.enums.Gallery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectionStudioResponse {
    private Long id;
    private String name;
    private String image;
    private String bluer;

    private Gallery orientation;

    public DirectionStudioResponse(Long id, String name, String image, String bluer, Gallery orientation) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.bluer = bluer;
        this.orientation = orientation;
    }
}
