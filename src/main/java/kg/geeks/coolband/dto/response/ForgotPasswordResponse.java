package kg.geeks.coolband.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ForgotPasswordResponse {

    private String token;

    private String secretKey;

    private HttpStatus httpStatus;

}
