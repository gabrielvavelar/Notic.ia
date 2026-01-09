package io.github.gabrielvavelar.Noticia.service.sender.email;

import io.github.gabrielvavelar.Noticia.dto.MessageDto;
import io.github.gabrielvavelar.Noticia.model.Subscriber;
import io.github.gabrielvavelar.Noticia.service.sender.email.impl.EmailService;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    private Subscriber subscriber;
    private MessageDto messageDto;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(emailService, "sender", "notic.ia@mail.com");

        subscriber = new Subscriber();
        subscriber.setEmail("cliente@email.com");
        messageDto = new MessageDto("subject", "content");
    }

    @Test
    void shouldSendMessage() throws Exception {

        MimeMessage mimeMessage = new MimeMessage((Session) null);
        Mockito.when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.send(subscriber, messageDto);

        Mockito.verify(mailSender, Mockito.times(1)).send(Mockito.any(MimeMessage.class));

        assertEquals("cliente@email.com", mimeMessage.getRecipients(Message.RecipientType.TO)[0].toString());
        assertEquals("subject", mimeMessage.getSubject());
    }

    @Test
    void shouldHandleExceptionAndLogWarning() {

        Mockito.when(mailSender.createMimeMessage()).thenThrow(new RuntimeException("SMTP Error"));

        assertDoesNotThrow(() -> emailService.send(subscriber, messageDto));
        Mockito.verify(mailSender, Mockito.never()).send(Mockito.any(MimeMessage.class));
    }
}
