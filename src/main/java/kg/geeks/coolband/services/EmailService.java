package kg.geeks.coolband.services;

import kg.geeks.coolband.dto.SimpleResponse;

public interface EmailService {
    SimpleResponse sendHtmlMessage(String email, String linkToChangePassword);
}
