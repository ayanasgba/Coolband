package kg.geeks.coolband.dto.response;

import kg.geeks.coolband.enums.Gallery;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AboutUsStudioResponse {

    private Long id;

    private String image;

    private Gallery orientation;

    private String bluer;

    private String youtubeUrl;

    public AboutUsStudioResponse(Long id, String image, Gallery orientation, String bluer, String youtubeUrl) {
        this.id = id;
        this.image = image;
        this.orientation = orientation;
        this.bluer = bluer;
        this.youtubeUrl = youtubeUrl;
    }
}
