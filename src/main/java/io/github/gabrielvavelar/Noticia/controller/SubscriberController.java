package io.github.gabrielvavelar.Noticia.controller;

import io.github.gabrielvavelar.Noticia.dto.SubscriberRequestDto;
import io.github.gabrielvavelar.Noticia.dto.SubscriberResponseDto;
import io.github.gabrielvavelar.Noticia.service.subscriber.SubscriberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Tag(name = "Subscribers")
public class SubscriberController {
    private final SubscriberService service;

    @PostMapping("/subscribe")
    public ResponseEntity<SubscriberResponseDto> subscribe(@RequestBody @Valid SubscriberRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.subscribe(requestDto));
    }

    @DeleteMapping("unsubscribe/{unsubscribeToken}")
    public ResponseEntity<Void> unsubscribe(@PathVariable UUID unsubscribeToken) {
        service.unsubscribe(unsubscribeToken);
        return ResponseEntity.ok().build();
    }
}
