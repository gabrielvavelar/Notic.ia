package io.github.gabrielvavelar.NewsLetter.mapper;

import io.github.gabrielvavelar.NewsLetter.dto.SubscriberRequestDto;
import io.github.gabrielvavelar.NewsLetter.dto.SubscriberResponseDto;
import io.github.gabrielvavelar.NewsLetter.model.Subscriber;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriberMapper {
    Subscriber toEntity(SubscriberRequestDto subscriberRequestDto);

    SubscriberResponseDto toResponse(Subscriber subscriber);
}
