package io.github.gabrielvavelar.Noticia.service.sender.email;

import io.github.gabrielvavelar.Noticia.dto.MessageDto;
import io.github.gabrielvavelar.Noticia.model.Subscriber;
import io.github.gabrielvavelar.Noticia.util.UnsubscribeLinkUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailComposer {
    private final TemplateEngine templateEngine;
    private final UnsubscribeLinkUtil unsubscribeLinkUtil;

    public MessageDto composeWelcomeEmail(Subscriber subscriber) {
        Context context = new Context();

        context.setVariable(
                "unsubscribeURL", unsubscribeLinkUtil.generateLink(subscriber.getUnsubscribeToken()
                ));

        String body = templateEngine.process("welcome-email", context);

        return new MessageDto(
                "Seja bem vindo ao Notic.ia",
                body
        );
    }

    public MessageDto composeNewsSummaryEmail(Subscriber subscriber,String newsSummary) {
        Context context = new Context();

        context.setVariable("newsSummary", newsSummary);

        context.setVariable(
                "unsubscribeURL", unsubscribeLinkUtil.generateLink(subscriber.getUnsubscribeToken()
                ));

        String body = templateEngine.process("news-summary-email", context);

        return new MessageDto(
                "Resumo diário de noticias",
                body
        );
    }
}
