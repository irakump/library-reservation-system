package com.library.backend.notifications;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private MailService mailService;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> messageCaptor;

    @Test
    void shouldSendToCorrectMail() {
        mailService.sendMail("joku@example.com", "Subject", "Message");

        verify(mailSender).send(messageCaptor.capture());
        assertThat(messageCaptor.getValue().getTo()).containsExactly("joku@example.com");
    }

    @Test
    void shouldSetCorrectSubject() {
        mailService.sendMail("joku@example.com", "Subject", "Message");

        verify(mailSender).send(messageCaptor.capture());
        assertThat(messageCaptor.getValue().getSubject()).isEqualTo("Subject");
    }

    @Test
    void shouldSetCorrectBody() {
        mailService.sendMail("joku@example.com", "Subject", "Message");

        verify(mailSender).send(messageCaptor.capture());
        assertThat(messageCaptor.getValue().getText()).isEqualTo("Message");
    }

    @Test
    void shouldAlwaysSendFromCorrectAddress() {
        mailService.sendMail("joku@example.com", "Subject", "Message");

        verify(mailSender).send(messageCaptor.capture());
        assertThat(messageCaptor.getValue().getFrom()).isEqualTo("metbook1@outlook.com");
    }

    @Test
    void shouldCallMailSenderExactlyOnce() {
        mailService.sendMail("joku@example.com", "Subject", "Message");

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
