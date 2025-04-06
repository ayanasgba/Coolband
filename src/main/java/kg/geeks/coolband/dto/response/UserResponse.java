package kg.geeks.coolband.dto.response;

import kg.geeks.coolband.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Abubakir Dev
 */
@Builder
@Getter @Setter
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private Role role;

    public UserResponse(Long id, String fullName, String email, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }
}