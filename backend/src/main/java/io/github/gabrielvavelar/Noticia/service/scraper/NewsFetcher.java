package io.github.gabrielvavelar.Noticia.service.scraper;

import io.github.gabrielvavelar.Noticia.model.NewsArticle;

import java.util.List;

public interface NewsFetcher {
    List<NewsArticle> fetchLatestNews(int limit);
}
