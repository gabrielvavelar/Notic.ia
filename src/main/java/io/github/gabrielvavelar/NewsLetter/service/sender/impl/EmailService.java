package io.github.gabrielvavelar.NewsLetter.service.sender.impl;

import io.github.gabrielvavelar.NewsLetter.service.sender.MessageSender;
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
    private String remetente;

    @Override
    public void send(String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetente);
            message.setSubject("Resumo diário");
            message.setText(content);
            message.setTo();

            mailSender.send(message);
        }
        catch (Exception e) {
            log.warn("Failed to send email", e);
        }
    }
}
