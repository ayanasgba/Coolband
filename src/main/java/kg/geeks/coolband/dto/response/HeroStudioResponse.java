package kg.geeks.coolband.dto.response;

import kg.geeks.coolband.enums.Gallery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeroStudioResponse {

    private Long id;

    private String title;

    private String text;

    private String video;

    private Gallery orientation;

    public HeroStudioResponse(Long id, String title, String text, String video, Gallery orientation) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.video = video;
        this.orientation = orientation;
    }
}
