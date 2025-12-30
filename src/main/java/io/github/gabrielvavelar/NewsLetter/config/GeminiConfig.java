package io.github.gabrielvavelar.NewsLetter.config;

import com.google.genai.Client;
import lombok.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GeminiProperties.class)
public class GeminiConfig {
    @Bean
    public Client geminiClient(GeminiProperties properties) {
        return Client.builder()
                .apiKey(properties.apiKey())
                .build();
    }
}
