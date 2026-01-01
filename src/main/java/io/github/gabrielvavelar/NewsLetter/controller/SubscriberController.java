package io.github.gabrielvavelar.NewsLetter.controller;

import io.github.gabrielvavelar.NewsLetter.dto.SubscriberRequestDto;
import io.github.gabrielvavelar.NewsLetter.dto.SubscriberResponseDto;
import io.github.gabrielvavelar.NewsLetter.service.subscriber.SubscriberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping
    public ResponseEntity<SubscriberResponseDto> unsubscribe(@RequestBody @Valid SubscriberRequestDto requestDto) {
        service.unsubscribe(requestDto);
        return ResponseEntity.noContent().build();
    }
}
