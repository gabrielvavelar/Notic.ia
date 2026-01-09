package io.github.gabrielvavelar.Noticia.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsArticle {
    private String title;
    private String content;
    private String url;

    public NewsArticle(String title, String content, String url) {
        this.title = title;
        this.content = content;
        this.url = url;
    }
}
