package io.github.gabrielvavelar.Noticia.service.sender.email;

import io.github.gabrielvavelar.Noticia.model.Subscriber;
import io.github.gabrielvavelar.Noticia.service.sender.MessageSender;
import io.github.gabrielvavelar.Noticia.util.UnsubscribeLinkUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WelcomeEmailService {

    private final MessageSender messageSender;

    public void send(Subscriber subscriber) {
        String content = """ 
                Bem-vindo à nossa Newsletter!
                
                Você receberá um resumo das principais notícias do dia, todos os dias às 7h da manhã.
                
               Para cancelar o recebimento de e-mails, acesse o link: %s
              
                """.formatted(UnsubscribeLinkUtil.generateLink(subscriber.getUnsubscribeToken()));

        messageSender.send(content, subscriber.getEmail());

    }
}
