package kg.geeks.coolband.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSuccessStudioResponse {

    private Long id;

    private String url;

    public StudentSuccessStudioResponse(Long id, String url) {
        this.id = id;
        this.url = url;
    }

}
