package io.github.gabrielvavelar.Noticia.service.sender.email;

import io.github.gabrielvavelar.Noticia.dto.MessageDto;
import io.github.gabrielvavelar.Noticia.model.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailComposerTest {
    @InjectMocks
    private EmailComposer emailComposer;

    @Mock
    private TemplateEngine templateEngine;

    private Subscriber subscriber;

    @BeforeEach
    void setup() {
        subscriber = new Subscriber();
        subscriber.setUnsubscribeToken(UUID.randomUUID());
    }

    @Test
    void shouldComposeWelcomeEmail() {

        when(templateEngine.process(Mockito.eq("welcome-email"), Mockito.any(Context.class)))
                .thenReturn("<html>body</html>");

        MessageDto result = emailComposer.composeWelcomeEmail(subscriber);

        assertEquals("Seja bem vindo ao Notic.ia", result.subject());
        assertEquals("<html>body</html>", result.content());

        Mockito.verify(templateEngine).process( Mockito.eq("welcome-email"),  Mockito.any(Context.class));
    }

    @Test
    void shouldComposeNewsSummaryEmail() {
        String summary = "News summary";

        when(templateEngine.process(Mockito.eq("news-summary-email"), Mockito.any(Context.class)))
                .thenReturn("<html>body</html>");

        MessageDto result = emailComposer.composeNewsSummaryEmail(subscriber, summary);

        assertEquals("Resumo diário de noticias", result.subject());
        assertEquals("<html>body</html>", result.content());

        Mockito.verify(templateEngine).process( Mockito.eq("news-summary-email"),  Mockito.any(Context.class));
    }

}
