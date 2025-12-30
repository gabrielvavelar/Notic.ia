package io.github.gabrielvavelar.NewsLetter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gemini.api")
public record GeminiProperties(
        String apiKey,
        String model
){}
