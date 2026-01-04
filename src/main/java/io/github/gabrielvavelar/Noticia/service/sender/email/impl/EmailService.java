package io.github.gabrielvavelar.Noticia.service.sender.email.impl;

import io.github.gabrielvavelar.Noticia.service.sender.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService implements MessageSender {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void send(String content, String email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setSubject("Resumo diário");
            message.setText(content);
            message.setTo(email);

            mailSender.send(message);
        }
        catch (Exception e) {
            log.warn("Failed to send email", e);
        }
    }
}
