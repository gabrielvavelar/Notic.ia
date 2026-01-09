package io.github.gabrielvavelar.Noticia.util;

import io.github.gabrielvavelar.Noticia.model.NewsArticle;

import java.util.List;

public class PromptUtil {

    private PromptUtil() {}

    public static String buildPrompt(List<NewsArticle> articles) {
        return basePrompt().formatted(formatArticles(articles));
    }

    public static String basePrompt() {
        return """
                Resuma as noticias coletadas hoje:
                
                Regras:
                - Sempre em português brasil.
                - Use somente as informações presentes no texto.
                - Não use emojis.
                - Não use formatação especial como **, listas decorativas ou markdown.
                - Garanta que cada resumo tenha um título.
                - Não mencione autores.
                
                %s
                """;
    }

    public static String formatArticles(List<NewsArticle> articles) {
        StringBuilder sb = new StringBuilder();
        for (NewsArticle article : articles) {
            sb.append(article.getTitle()).append("\n");
            sb.append(article.getContent()).append("\n");
        }
        return sb.toString();
    }
}
