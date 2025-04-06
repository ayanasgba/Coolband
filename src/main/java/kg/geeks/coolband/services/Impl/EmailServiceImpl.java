package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.config.EmailSenderConfig;
import kg.geeks.coolband.config.JwtService;
import kg.geeks.coolband.config.WebConfig;
import kg.geeks.coolband.dto.SimpleResponse;
import kg.geeks.coolband.dto.response.ForgotPasswordResponse;
import kg.geeks.coolband.entities.User;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.UserRepository;
import kg.geeks.coolband.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final EmailSenderConfig emailSenderConfig;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    @Override
    public SimpleResponse sendHtmlMessage(String email, String linkToChangePassword) {
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> {
            log.info("User is not found !!!");
            return new NotFoundException("Пользователь не найден !!!");
        });
        String token = jwtService.generateToken(user);
        String subject = "reset";
        String from = "bolsunbekov.akylbek@gmail.com";
        String templateName = "reset_password";
        Context context = new Context();
        context.setVariable("userName", user.getFirstName() + "!");
        context.setVariable("link", WebConfig.getFrontServer() + "/" + linkToChangePassword + "?token=%s".formatted(token));
        emailSenderConfig.sendEmailWithHTMLTemplate(user.getEmail(), from, subject, templateName, context);
        log.info("Method Send Email With HTML is working !!!");
        return SimpleResponse.builder()
                .message("Link successfully created !")
                .httpStatus(HttpStatus.OK)
                .build();
    }
}