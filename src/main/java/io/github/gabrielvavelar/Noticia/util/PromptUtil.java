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
                Você é um editor de newsletter experiente;
                Abaixo estão os artigos coletados hoje:
                
                %s
                
                Com base neles, escreva um resumo atraente para os inscritos.
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
