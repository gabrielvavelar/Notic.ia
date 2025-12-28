package io.github.gabrielvavelar.NewsLetter.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsArticle {
    private String title;
    private String description;
    private String url;

    public NewsArticle(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }
}
