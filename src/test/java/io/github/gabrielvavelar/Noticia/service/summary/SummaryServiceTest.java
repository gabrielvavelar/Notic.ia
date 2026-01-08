package io.github.gabrielvavelar.Noticia.service.summary;

import com.google.genai.Client;
import com.google.genai.Models;
import com.google.genai.types.GenerateContentResponse;
import io.github.gabrielvavelar.Noticia.config.GeminiProperties;
import io.github.gabrielvavelar.Noticia.exception.InvalidSummaryInputException;
import io.github.gabrielvavelar.Noticia.exception.SummaryGenerationException;
import io.github.gabrielvavelar.Noticia.model.NewsArticle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SummaryServiceTest {

    @InjectMocks
    private SummaryService summaryService;

    @Mock
    private GeminiProperties properties;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Models models;

    @Mock
    private Client client;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(client, "models", models);
    }

    @Test
    void shouldThrowInvalidSummaryInputException() {
        assertThrows(InvalidSummaryInputException.class, () -> {
            summaryService.generateSummary(Collections.emptyList());
        });
    }

    @Test
    void shouldGenerateSummary() {
        // Arrange
        List<NewsArticle> articles = List.of(
                new NewsArticle("title", "content", "url")
        );
        String model = "gemini-1.5-flash";
        String output = "output";

        Mockito.when(properties.model()).thenReturn(model);

        GenerateContentResponse mockResponse = Mockito.mock(GenerateContentResponse.class);
        Mockito.when(mockResponse.text()).thenReturn(output);

        Mockito.when(models.generateContent(Mockito.eq(model), Mockito.anyString(), Mockito.any()))
                .thenReturn(mockResponse);

        String result = summaryService.generateSummary(articles);

        assertEquals(output, result);
    }
    
    @Test
    void shouldThrowSummaryGenerationException() {
        List<NewsArticle> articles = List.of(
                new NewsArticle("title", "content", "url")
        );
        Mockito.when(properties.model()).thenReturn("gemini-pro");

        Mockito.when(models.generateContent(Mockito.anyString(), Mockito.anyString(),Mockito.any()))
                .thenThrow(new RuntimeException());

        assertThrows(SummaryGenerationException.class, () -> {
            summaryService.generateSummary(articles);
        });
    }
}
