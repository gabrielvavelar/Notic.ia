package io.github.gabrielvavelar.Noticia.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UnsubscribeLinkUtil {

    private final String baseUrl;

    public UnsubscribeLinkUtil(@Value("${url}") String url) {
        this.baseUrl = url + "/unsubscribe.html?token=";
    }

    public String generateLink(UUID token) {
        return baseUrl + token;
    }
}
