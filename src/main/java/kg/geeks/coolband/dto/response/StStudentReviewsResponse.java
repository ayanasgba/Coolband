package kg.geeks.coolband.dto.response;

import kg.geeks.coolband.enums.Gallery;
import lombok.*;

@Setter
@Getter
@Builder
public class StStudentReviewsResponse {

    private Long id;

    private String name;

    private String image;

    private String bluer;

    private String reviews;

    private Gallery orientation;

    public StStudentReviewsResponse(Long id, String name, String image, String bluer, String reviews, Gallery orientation) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.bluer = bluer;
        this.reviews = reviews;
        this.orientation = orientation;
    }
}
