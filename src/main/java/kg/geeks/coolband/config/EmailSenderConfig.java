package kg.geeks.coolband.config;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kg.geeks.coolband.exceptions.BadCredentialException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderConfig implements EmailSenderService{

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;


    @Override
    public void sendEmailWithHTMLTemplate(String to, String from, String subject, String template, Context context) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            messageHelper.setTo(to);
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            String htmlContent = templateEngine.process(template, context);
            messageHelper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
            log.info("Method Send works !!!");
        } catch (MessagingException e) {
            throw new BadCredentialException("Выдает ошибку : " + e.getMessage());
        }
    }
}
