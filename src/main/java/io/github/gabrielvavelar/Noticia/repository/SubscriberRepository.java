package io.github.gabrielvavelar.Noticia.repository;

import io.github.gabrielvavelar.Noticia.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, UUID> {
    boolean existsByEmail(String email);
    Optional<Subscriber> findByUnsubscribeToken(UUID unsubscribeToken);
}
