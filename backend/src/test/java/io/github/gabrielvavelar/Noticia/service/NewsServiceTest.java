package io.github.gabrielvavelar.Noticia.service;

import io.github.gabrielvavelar.Noticia.dto.MessageDto;
import io.github.gabrielvavelar.Noticia.model.NewsArticle;
import io.github.gabrielvavelar.Noticia.model.Subscriber;
import io.github.gabrielvavelar.Noticia.service.scraper.NewsFetcher;
import io.github.gabrielvavelar.Noticia.service.sender.MessageSender;
import io.github.gabrielvavelar.Noticia.service.sender.email.EmailComposer;
import io.github.gabrielvavelar.Noticia.service.subscriber.SubscriberService;
import io.github.gabrielvavelar.Noticia.service.summary.SummaryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest {

    @InjectMocks
    private NewsService newsService;

    @Mock
    private NewsFetcher newsFetcher;

    @Mock
    private MessageSender messageSender;

    @Mock
    private SubscriberService subscriberService;

    @Mock
    private SummaryService summaryService;

    @Mock
    private EmailComposer emailComposer;

    @Test
    void shouldSendNewsSummaryToSubscribers() {
        var subscribers = List.of(new Subscriber(), new Subscriber());
        var news = List.of(
                new NewsArticle("title", "content", "url")
        );
        var summary = "summary";
        var message = new MessageDto("subject", "content");

        Mockito.when(subscriberService.getAllSubscribers()).thenReturn(subscribers);
        Mockito.when(newsFetcher.fetchLatestNews(20)).thenReturn(news);
        Mockito.when(summaryService.generateSummary(news)).thenReturn(summary);
        Mockito.when(emailComposer.composeNewsSummaryEmail(Mockito.any(), Mockito.eq(summary))).thenReturn(message);

        newsService.sendNewsSummaryToSubscribers();

        Mockito.verify(messageSender, Mockito.times(2))
                .send(Mockito.any(Subscriber.class), Mockito.eq(message));

        Mockito.verify(newsFetcher).fetchLatestNews(20);
    }
}