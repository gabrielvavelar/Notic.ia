package io.github.gabrielvavelar.Noticia.service.scraper.impl;

import io.github.gabrielvavelar.Noticia.exception.NewsScrapingException;
import io.github.gabrielvavelar.Noticia.model.NewsArticle;
import io.github.gabrielvavelar.Noticia.service.scraper.HtmlLoader;
import io.github.gabrielvavelar.Noticia.service.scraper.NewsFetcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class G1WebScraper implements NewsFetcher {

    private final HtmlLoader htmlLoader;

    @Override
    public List<NewsArticle> fetchLatestNews(int limit) {
        try {
            Document home = htmlLoader.load("https://g1.globo.com/");

            List<String> articleUrls = home.select("a[href]")
                    .stream()
                    .map(a -> a.attr("abs:href"))
                    .filter(href -> href.endsWith(".ghtml"))
                    .distinct()
                    .limit(limit)
                    .toList();

            List<NewsArticle> articles = new ArrayList<>();

            for (String url : articleUrls) {
                try {
                    Document article = htmlLoader.load(url);

                    String title = article.select("h1").text();
                    String content = article.select("article").text();

                    articles.add(new NewsArticle(title, content, url));

                } catch (Exception e) {
                    log.warn("Failed to process article {}", url, e);
                }
            }

            return articles;

        } catch (Exception e) {
            throw new NewsScrapingException("Scraping error", e);
        }
    }
}
