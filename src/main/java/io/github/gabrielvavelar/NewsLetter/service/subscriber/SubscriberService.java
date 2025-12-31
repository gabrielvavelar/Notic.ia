package io.github.gabrielvavelar.NewsLetter.service.subscriber;

import io.github.gabrielvavelar.NewsLetter.dto.SubscriberRequestDto;
import io.github.gabrielvavelar.NewsLetter.dto.SubscriberResponseDto;
import io.github.gabrielvavelar.NewsLetter.exception.EmailAlreadyExistsException;
import io.github.gabrielvavelar.NewsLetter.mapper.SubscriberMapper;
import io.github.gabrielvavelar.NewsLetter.model.Subscriber;
import io.github.gabrielvavelar.NewsLetter.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriberService {

    private final SubscriberRepository repository;
    private final SubscriberMapper mapper;

    public SubscriberResponseDto subscribe(SubscriberRequestDto requestDto) {
        if(repository.existsByEmail(requestDto.email())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Subscriber subscriber = mapper.toEntity(requestDto);
        Subscriber saved = repository.save(subscriber);

        return mapper.toResponse(saved);
    }

    public List<String> getAllActiveEmails() {
        return repository.findAllByActiveTrue()
                .stream()
                .map(Subscriber::getEmail)
                .toList();
    }
}
