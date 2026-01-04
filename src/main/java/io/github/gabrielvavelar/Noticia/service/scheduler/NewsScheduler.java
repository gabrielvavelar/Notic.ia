package io.github.gabrielvavelar.Noticia.service.scheduler;

import io.github.gabrielvavelar.Noticia.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsScheduler {
    private final NewsService newsService;

    @Scheduled(cron = "0 0 7 * * *")
    public void sendDailyNewsSummary(){
        newsService.sendNewsSummaryToSubscribers();
    }
}
