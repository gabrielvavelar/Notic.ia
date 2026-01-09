package io.github.gabrielvavelar.Noticia.service.scraper.impl;

import io.github.gabrielvavelar.Noticia.service.scraper.HtmlLoader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsoupHtmlLoader implements HtmlLoader {
    @Override
    public Document load(String url) throws IOException {
        return Jsoup.connect(url).userAgent("Mozilla/5.0").get();
    }
}
