package io.github.gabrielvavelar.NewsLetter.service.scrapper;

import io.github.gabrielvavelar.NewsLetter.model.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NewsFetcher {
    List<NewsArticle> fetchLatestNews(int limit);
}
