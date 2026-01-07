package io.github.gabrielvavelar.Noticia.service.subscriber;

import io.github.gabrielvavelar.Noticia.dto.MessageDto;
import io.github.gabrielvavelar.Noticia.dto.SubscriberRequestDto;
import io.github.gabrielvavelar.Noticia.dto.SubscriberResponseDto;
import io.github.gabrielvavelar.Noticia.exception.EmailAlreadyExistsException;
import io.github.gabrielvavelar.Noticia.exception.InvalidUnsubscribeTokenException;
import io.github.gabrielvavelar.Noticia.mapper.SubscriberMapper;
import io.github.gabrielvavelar.Noticia.model.Subscriber;
import io.github.gabrielvavelar.Noticia.repository.SubscriberRepository;
import io.github.gabrielvavelar.Noticia.service.sender.MessageSender;
import io.github.gabrielvavelar.Noticia.service.sender.email.EmailComposer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@ExtendWith(MockitoExtension.class)
public class SubscriberServiceTest {

    @InjectMocks
    private SubscriberService subscriberService;

    @Mock
    private SubscriberRepository subscriberRepository;

    @Mock
    private SubscriberMapper subscriberMapper;

    @Mock
    private EmailComposer emailComposer;

    @Mock
    private MessageSender messageSender;


    @Test
    void shoudlSaveSubscriber() {
        SubscriberRequestDto requestDto = new SubscriberRequestDto("subscriber@email.com");
        Subscriber subscriber = new Subscriber(requestDto.email());
        subscriber.setId(UUID.randomUUID());

        Subscriber savedSubscriber = new Subscriber(requestDto.email());
        savedSubscriber.setId(subscriber.getId());
        savedSubscriber.setUnsubscribeToken(UUID.randomUUID());

        SubscriberResponseDto responseDto = new SubscriberResponseDto(
                savedSubscriber.getId(),
                savedSubscriber.getEmail(),
                savedSubscriber.getUnsubscribeToken()
        );

        MessageDto message = new MessageDto("subject", "content");

        Mockito.when(subscriberRepository.existsByEmail(requestDto.email())).thenReturn(false);
        Mockito.when(subscriberMapper.toEntity(requestDto)).thenReturn(subscriber);
        Mockito.when(subscriberRepository.save(subscriber)).thenReturn(savedSubscriber);
        Mockito.when(subscriberMapper.toResponse(savedSubscriber)).thenReturn(responseDto);
        Mockito.when(emailComposer.composeWelcomeEmail(subscriber)).thenReturn(message);

        SubscriberResponseDto result = subscriberService.subscribe(requestDto);

        assertThat(result).isNotNull();
        assertThat(result.email()).isEqualTo(requestDto.email());
        assertThat(result.unsubscribeToken()).isNotNull();

        Mockito.verify(subscriberRepository).existsByEmail(requestDto.email());
        Mockito.verify(subscriberRepository).save(subscriber);
        Mockito.verify(subscriberMapper).toEntity(requestDto);
        Mockito.verify(subscriberMapper).toResponse(savedSubscriber);
        Mockito.verify(messageSender).send(subscriber, message);
    }

    @Test
    void shouldThrowEmailAlreadyExistsException() {
        SubscriberRequestDto requestDto = new SubscriberRequestDto("subscriber@email.com");

        Mockito.when(subscriberRepository.existsByEmail(requestDto.email())).thenReturn(true);

        var error = catchThrowable(() -> subscriberService.subscribe(requestDto));
        assertThat(error).isInstanceOf(EmailAlreadyExistsException.class);

        Mockito.verify(subscriberRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldDeleteSubscriber() {
        Subscriber subscriber = new Subscriber();
        UUID unsubscribeToken = UUID.randomUUID();

        Mockito.when(subscriberRepository.findByUnsubscribeToken(unsubscribeToken))
                .thenReturn(Optional.of(subscriber));

        subscriberService.unsubscribe(unsubscribeToken);

        Mockito.verify(subscriberRepository).findByUnsubscribeToken(unsubscribeToken);
        Mockito.verify(subscriberRepository).delete(subscriber);
    }

    @Test
    void shouldThrowInvalidUnsubscribeTokenException() {
        UUID unsubscribeToken = UUID.randomUUID();

        Mockito.when(subscriberRepository.findByUnsubscribeToken(unsubscribeToken))
                .thenReturn(Optional.empty());

        var error = catchThrowable(() -> subscriberService.unsubscribe(unsubscribeToken));
        assertThat(error).isInstanceOf(InvalidUnsubscribeTokenException.class);

        Mockito.verify(subscriberRepository,  Mockito.never()).delete(Mockito.any());
    }
}
