package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import kg.geeks.coolband.dto.SimpleResponse;
import kg.geeks.coolband.dto.request.ForgotPasswordRequest;
import kg.geeks.coolband.dto.request.SignInRequest;
import kg.geeks.coolband.dto.request.SignUpRequest;
import kg.geeks.coolband.dto.request.UpdateForAdminRequest;
import kg.geeks.coolband.dto.response.*;
import kg.geeks.coolband.services.AuthenticationService;
import kg.geeks.coolband.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final EmailService emailService;

    @PostMapping("/signIn")
    @PermitAll
    public AuthenticationSignInResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authenticationService.signIn(signInRequest);
    }

    @PostMapping("/signUp")
    @PermitAll
    public AuthenticationSignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return authenticationService.signUp(signUpRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public UpdateForAdminResponse updateForAdmin(@PathVariable Long id, @RequestBody UpdateForAdminRequest update) {
        return authenticationService.updateAdmin(id, update);
    }

    @PutMapping("/forgotPassword")
    @Operation(summary = "Забыли пароль",
            description = "Отправить ссылку пользователю на email для смены пароля")
    public SimpleResponse forgotPassword(@RequestParam String email,
                                                 @RequestParam String linkToChangePassword) {
        return emailService.sendHtmlMessage(email, linkToChangePassword);
    }

    @PutMapping("/changePassword")
    @Operation(summary = "Изменить пароль", description = "Метод для смены пароля")
    public SimpleResponse changePasswordOnForget(@RequestBody ForgotPasswordRequest forgotPassword) {
        return authenticationService.changeOnForgot(forgotPassword);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Метод для получения всех пользователей")
    @GetMapping("/getAllUsers")
    public List<UserResponse> getAllUser() {
        return authenticationService.getAllUser();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Метод для удаление пользователя")
    @DeleteMapping("/{userId}")
    public SimpleResponse deleteUserById(@PathVariable Long userId) {
        return authenticationService.deleteUserById(userId);
    }

    @Operation(summary = "Метод для добавление менеджера",
            description = "Только Админ может добавить менеджера")
    @PostMapping("/signUpForManager")
    public AuthenticationSignUpResponse createManager(@RequestBody SignUpRequest sign){
        return authenticationService.signUpForManager(sign);
    }
}