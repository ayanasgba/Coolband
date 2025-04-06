package kg.geeks.coolband.dto.response;

import kg.geeks.coolband.enums.Gallery;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EventStudioImagesResponse {

    private Long id;

    private Long albumId;

    private String image;

    private String originalImage;

    private Gallery orientation;

    private boolean isCoverImage;

    private String bluer;

    public EventStudioImagesResponse(Long id, Long albumId, String image, String originalImage, Gallery orientation, boolean isCoverImage, String bluer) {
        this.id = id;
        this.albumId = albumId;
        this.image = image;
        this.originalImage = originalImage;
        this.orientation = orientation;
        this.isCoverImage = isCoverImage;
        this.bluer = bluer;
    }
}
