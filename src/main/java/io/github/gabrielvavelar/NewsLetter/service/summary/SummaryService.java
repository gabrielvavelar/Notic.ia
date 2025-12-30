package io.github.gabrielvavelar.NewsLetter.service.summary;

import com.google.genai.Client;
import io.github.gabrielvavelar.NewsLetter.config.GeminiProperties;
import io.github.gabrielvavelar.NewsLetter.exception.SummaryGenerationException;
import io.github.gabrielvavelar.NewsLetter.model.NewsArticle;
import io.github.gabrielvavelar.NewsLetter.prompt.SummaryPromptBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.google.genai.types.GenerateContentResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryService {
    private final Client client;
    private final GeminiProperties properties;
    private final SummaryPromptBuilder promptBuilder;

    public String generateSummary(List<NewsArticle> articles) {
        try{
            GenerateContentResponse response =
                    client.models.generateContent(
                            properties.model(),
                            promptBuilder.build(articles),
                            null);

            if(response == null || response.text() == null || response.text().isBlank()){
               throw new SummaryGenerationException("Gemini returned an empty response");
               }

            return response.text();

        }
        catch (Exception e){
            throw new SummaryGenerationException("Failed to generate Gemini summary");
        }
    }
}
