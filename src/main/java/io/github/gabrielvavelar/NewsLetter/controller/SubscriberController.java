package io.github.gabrielvavelar.NewsLetter.controller;

import io.github.gabrielvavelar.NewsLetter.dto.SubscriberRequestDto;
import io.github.gabrielvavelar.NewsLetter.dto.SubscriberResponseDto;
import io.github.gabrielvavelar.NewsLetter.service.subscriber.SubscriberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscribers")
@RequiredArgsConstructor
public class SubscriberController {
    private final SubscriberService service;

    @PostMapping
    public ResponseEntity<SubscriberResponseDto> subscribe(@RequestBody @Valid SubscriberRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.subscribe(requestDto));
    }
}
