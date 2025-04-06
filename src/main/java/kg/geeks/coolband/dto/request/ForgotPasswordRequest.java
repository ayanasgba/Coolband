package kg.geeks.coolband.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordRequest {

    @NotNull
    @NotBlank
    private String newPassword;

    @NotNull
    @NotBlank
    private String verifyPassword;
}
