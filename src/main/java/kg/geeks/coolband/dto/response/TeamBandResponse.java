package kg.geeks.coolband.dto.response;

import kg.geeks.coolband.enums.Gallery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamBandResponse {

    private Long id;

    private String name;

    private String image;

    private String video;

    private String instrument;

    private Gallery orientation;

    private String bluer;

    public TeamBandResponse(Long id, String name, String image, String video, String instrument, Gallery orientation, String bluer) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.video = video;
        this.instrument = instrument;
        this.orientation = orientation;
        this.bluer = bluer;
    }
}
