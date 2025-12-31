package io.github.gabrielvavelar.NewsLetter.dto;

import java.util.UUID;

public record SubscriberResponseDto(
        UUID id,
        String email,
        boolean active
) {}
