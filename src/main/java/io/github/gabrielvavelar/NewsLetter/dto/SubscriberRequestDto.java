package io.github.gabrielvavelar.NewsLetter.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SubscriberRequestDto(
        @NotBlank(message = "Email must not be blank")
        @Email(message = "Please provide a valid email address")
        String email
) {}
