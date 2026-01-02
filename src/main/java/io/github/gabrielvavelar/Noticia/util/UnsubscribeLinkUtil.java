package io.github.gabrielvavelar.Noticia.util;

import java.util.UUID;

public class UnsubscribeLinkUtil {

    private static final String BASE_URL = "http://localhost:8080/subscribers/";

    private UnsubscribeLinkUtil() {}

    public static String generateLink(UUID token) {
        return BASE_URL + token.toString();
    }
}
