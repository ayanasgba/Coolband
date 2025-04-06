package kg.geeks.coolband.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateForAdminRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
