package kg.geeks.coolband.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateForAdminResponse {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
