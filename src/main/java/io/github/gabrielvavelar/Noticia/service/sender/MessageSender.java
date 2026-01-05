package io.github.gabrielvavelar.Noticia.service.sender;

import io.github.gabrielvavelar.Noticia.model.Subscriber;

public interface MessageSender {
    void send(Subscriber subscriber, String content);
}
