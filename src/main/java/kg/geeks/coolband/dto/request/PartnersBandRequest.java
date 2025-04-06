package kg.geeks.coolband.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PartnersBandRequest {

    @NotNull
    private MultipartFile image;

    @NotNull
    private String bluer;

    @NotNull
    private String url;

}
