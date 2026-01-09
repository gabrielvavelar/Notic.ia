package io.github.gabrielvavelar.Noticia.service.scraper;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface HtmlLoader {
    Document load(String url) throws IOException;
}
