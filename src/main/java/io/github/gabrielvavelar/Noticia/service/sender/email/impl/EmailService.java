package io.github.gabrielvavelar.Noticia.service.sender.email.impl;

import io.github.gabrielvavelar.Noticia.model.Subscriber;
import io.github.gabrielvavelar.Noticia.service.sender.MessageSender;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService implements MessageSender {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void send(Subscriber subscriber, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            mimeHelper.setFrom(sender);
            mimeHelper.setTo(subscriber.getEmail());
            mimeHelper.setSubject("Notic.ai");
            mimeHelper.setText(content, true);

            mailSender.send(mimeMessage);
        }
        catch (Exception e) {
            log.warn("Failed to send email", e);
        }
    }
}
