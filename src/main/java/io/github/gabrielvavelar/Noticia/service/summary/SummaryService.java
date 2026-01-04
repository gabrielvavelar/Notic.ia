package io.github.gabrielvavelar.Noticia.service.summary;

import com.google.genai.Client;
import io.github.gabrielvavelar.Noticia.config.GeminiProperties;
import io.github.gabrielvavelar.Noticia.exception.InvalidSummaryInputException;
import io.github.gabrielvavelar.Noticia.exception.SummaryGenerationException;
import io.github.gabrielvavelar.Noticia.model.NewsArticle;
import io.github.gabrielvavelar.Noticia.util.PromptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.google.genai.types.GenerateContentResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryService {
    private final Client client;
    private final GeminiProperties properties;

    public String generateSummary(List<NewsArticle> articles) {
        if (articles == null || articles.isEmpty()) {
            throw new InvalidSummaryInputException("No articles available to summarize");
        }
        try{
            GenerateContentResponse response =
                    client.models.generateContent(
                            properties.model(),
                            PromptUtil.buildPrompt(articles),
                            null);

            if(response == null || response.text() == null || response.text().isBlank()){
               throw new SummaryGenerationException("Gemini returned an empty response");
            }

            return response.text();
        }
        catch (Exception e){
            throw new SummaryGenerationException("Failed to generate Gemini summary", e);
        }
    }
}
