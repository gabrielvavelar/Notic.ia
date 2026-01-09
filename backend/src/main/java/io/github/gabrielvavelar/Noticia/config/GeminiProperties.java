package io.github.gabrielvavelar.Noticia.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "gemini.api")
public record GeminiProperties(
        @NotBlank(message = "Gemini api key must not be blank")
        String apiKey,
        @NotBlank(message = "Gemini api model must not be blank")
        String model
){}
