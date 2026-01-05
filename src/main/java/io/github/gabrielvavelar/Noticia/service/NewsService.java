package io.github.gabrielvavelar.Noticia.service;

import io.github.gabrielvavelar.Noticia.model.Subscriber;
import io.github.gabrielvavelar.Noticia.service.scraper.NewsFetcher;
import io.github.gabrielvavelar.Noticia.service.sender.MessageSender;
import io.github.gabrielvavelar.Noticia.service.sender.email.EmailComposer;
import io.github.gabrielvavelar.Noticia.service.subscriber.SubscriberService;
import io.github.gabrielvavelar.Noticia.service.summary.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsFetcher newsFetcher;
    private final MessageSender messageSender;
    private final SubscriberService subscriberService;
    private final SummaryService summaryService;
    private final EmailComposer emailComposer;

    public void sendNewsSummaryToSubscribers() {
        var subscribers = subscriberService.getAllSubscribers();

        String summary = summaryService.generateSummary(newsFetcher.fetchLatestNews(20));

        for(Subscriber subscriber : subscribers) {
            String content = emailComposer.composeNewsSummaryEmail(subscriber, summary);
            messageSender.send(subscriber, content);
        }
    }
}
