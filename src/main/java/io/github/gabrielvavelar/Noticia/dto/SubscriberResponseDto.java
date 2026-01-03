package io.github.gabrielvavelar.Noticia.dto;

import java.util.UUID;

public record SubscriberResponseDto(
        UUID id,
        String email,
        UUID unsubscribeToken
) {}
