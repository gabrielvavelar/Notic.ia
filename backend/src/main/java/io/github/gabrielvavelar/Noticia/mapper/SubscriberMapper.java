package io.github.gabrielvavelar.Noticia.mapper;

import io.github.gabrielvavelar.Noticia.dto.SubscriberRequestDto;
import io.github.gabrielvavelar.Noticia.dto.SubscriberResponseDto;
import io.github.gabrielvavelar.Noticia.model.Subscriber;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriberMapper {
    Subscriber toEntity(SubscriberRequestDto subscriberRequestDto);

    SubscriberResponseDto toResponse(Subscriber subscriber);
}
