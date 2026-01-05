package io.github.gabrielvavelar.Noticia.service.subscriber;

import io.github.gabrielvavelar.Noticia.dto.SubscriberRequestDto;
import io.github.gabrielvavelar.Noticia.dto.SubscriberResponseDto;
import io.github.gabrielvavelar.Noticia.exception.EmailAlreadyExistsException;
import io.github.gabrielvavelar.Noticia.exception.InvalidUnsubscribeTokenException;
import io.github.gabrielvavelar.Noticia.mapper.SubscriberMapper;
import io.github.gabrielvavelar.Noticia.model.Subscriber;
import io.github.gabrielvavelar.Noticia.repository.SubscriberRepository;
import io.github.gabrielvavelar.Noticia.service.sender.MessageSender;
import io.github.gabrielvavelar.Noticia.service.sender.email.EmailComposer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriberService {

    private final SubscriberRepository repository;
    private final SubscriberMapper mapper;
    private final EmailComposer emailComposer;
    private final MessageSender messageSender;

    public SubscriberResponseDto subscribe(SubscriberRequestDto requestDto) {
        if(repository.existsByEmail(requestDto.email())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        Subscriber subscriber = mapper.toEntity(requestDto);

        subscriber.setUnsubscribeToken(UUID.randomUUID());

        Subscriber saved = repository.save(subscriber);

        String content = emailComposer.composeWelcomeEmail(subscriber);
        messageSender.send(subscriber, content);

        return mapper.toResponse(saved);
    }

    public void unsubscribe(UUID unsubscribeToken) {
        Subscriber subscriber = repository.findByUnsubscribeToken(unsubscribeToken)
                .orElseThrow(() ->
                        new InvalidUnsubscribeTokenException("Invalid unsubscribe token")
                );

        repository.delete(subscriber);
    }

    public List<Subscriber> getAllSubscribers() {
        return repository.findAll();
    }
}
