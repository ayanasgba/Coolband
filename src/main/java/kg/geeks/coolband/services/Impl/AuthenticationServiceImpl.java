package kg.geeks.coolband.services.Impl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import kg.geeks.coolband.config.JwtService;
import kg.geeks.coolband.dto.SimpleResponse;
import kg.geeks.coolband.dto.request.ForgotPasswordRequest;
import kg.geeks.coolband.dto.request.SignInRequest;
import kg.geeks.coolband.dto.request.SignUpRequest;
import kg.geeks.coolband.dto.request.UpdateForAdminRequest;
import kg.geeks.coolband.dto.response.AuthenticationSignInResponse;
import kg.geeks.coolband.dto.response.AuthenticationSignUpResponse;
import kg.geeks.coolband.dto.response.UpdateForAdminResponse;
import kg.geeks.coolband.dto.response.UserResponse;
import kg.geeks.coolband.entities.User;
import kg.geeks.coolband.enums.Role;
import kg.geeks.coolband.exceptions.AlreadyExistException;
import kg.geeks.coolband.exceptions.BadCredentialException;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.UserRepository;
import kg.geeks.coolband.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationSignInResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.getEmail()).orElseThrow(() ->
                new NotFoundException(String.format("Пользователь с адресом электронной почты: %s не найден!", signInRequest.getEmail())));

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            log.info("Недействительный пароль");
            throw new BadCredentialException("Недействительный пароль");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(),
                        signInRequest.getPassword()));
        String token = jwtService.generateToken(user);
        return AuthenticationSignInResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .token(token)
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationSignUpResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistException("Пользователь с адресом электронной почты:"
                    + signUpRequest.getEmail() + " уже существует");
        }
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        log.info("Пользователь успешно сохранен с идентификатором:" + user.getId());
        String token = jwtService.generateToken(user);
        return new AuthenticationSignUpResponse(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                token,
                user.getEmail(),
                user.getRole()
        );
    }

    @PostConstruct
    public void initSaveAdmin() {
        User user = User.builder()
                .firstName("Anarkin")
                .lastName("Dair")
                .email("is.anarkin@gmail.com")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ADMIN)
                .build();
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
            log.info("Admin successfully is saved with id : %s".formatted(user.getId()));
        }
    }

    @Override
    public UpdateForAdminResponse updateAdmin(Long id, UpdateForAdminRequest update) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getUserByEmail(email).orElse(null);
        if (user != null && (user.getRole().equals(Role.ADMIN))) {
            User user1 = userRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("User with id: %s not found".formatted(id)));
            user1.setFirstName(update.getFirstName());
            user1.setLastName(update.getLastName());
            user1.setEmail(update.getEmail());
            user1.setPassword(passwordEncoder.encode(update.getPassword()));
            userRepository.save(user1);
            return UpdateForAdminResponse.builder()
                    .firstName(user1.getFirstName())
                    .lastName(user1.getLastName())
                    .email(user1.getEmail())
                    .password(user1.getPassword())
                    .build();
        } else throw new BadCredentialException("Access denied");
    }

    @Override
    public SimpleResponse changeOnForgot(ForgotPasswordRequest newPasswordRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Access Denied Exception !!!");
        }
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> {
            log.error("User with email is not found !!!");
            return new NotFoundException("User is not found !!!");
        });
        if (newPasswordRequest.getNewPassword().equals(newPasswordRequest.getVerifyPassword())) {
            user.setPassword(passwordEncoder.encode(newPasswordRequest.getNewPassword()));
            userRepository.save(user);
            return new SimpleResponse(
                    HttpStatus.OK,
                    "Пароль успешно обновлен"
            );
        } else {
            return new SimpleResponse(
                    HttpStatus.BAD_REQUEST,
                    "Пожалуйста, удостоверьтесь, что введенные пароли идентичны"
            );
        }
    }

    @Override
    public List<UserResponse> getAllUser() {
        return userRepository.getAllUser();
    }

    @Override
    public SimpleResponse deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new BadCredentialException("User with id : %s not exist".formatted(userId));
        }
        userRepository.deleteById(userId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("User with id : %s success deleted".formatted(userId))
                .build();
    }

    @Override
    public AuthenticationSignUpResponse signUpForManager(SignUpRequest sign) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getUserByEmail(email).orElse(null);
        if (user != null && user.getRole().equals(Role.ADMIN)) {
            User user1 = new User();
            user1.setFirstName(sign.getFirstName());
            user1.setLastName(sign.getLastName());
            user1.setRole(Role.MANAGER);
            user1.setEmail(sign.getEmail());
            user1.setPassword(passwordEncoder.encode(sign.getPassword()));
            if (user1.getRole().equals(Role.MANAGER))
                userRepository.save(user1);
            String token = jwtService.generateToken(user1);
            return AuthenticationSignUpResponse.builder()
                    .id(user1.getId())
                    .fullName(sign.getFirstName() + " " + sign.getLastName())
                    .token(token)
                    .role(Role.MANAGER)
                    .email(sign.getEmail())
                    .build();
        } else throw new BadCredentialException("Sorry you are not an Admin you do not have access");
    }
}