package com.library.backend.notifications;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(final JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendMail(final String sendTo, final String subject, final String text) {
        final SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("metbook1@outlook.com");
        msg.setTo(sendTo);
        msg.setSubject(subject);
        msg.setText(text);

        mailSender.send(msg);
    }
}
