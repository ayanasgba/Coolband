package kg.geeks.coolband.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartnersBandResponse {

    private Long id;

    private String image;

    private String bluer;

    private String url;

    public PartnersBandResponse(Long id, String image, String bluer, String url) {
        this.id = id;
        this.image = image;
        this.bluer = bluer;
        this.url = url;
    }

}
