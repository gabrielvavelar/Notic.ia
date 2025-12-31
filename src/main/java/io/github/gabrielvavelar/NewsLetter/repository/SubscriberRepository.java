package io.github.gabrielvavelar.NewsLetter.repository;

import io.github.gabrielvavelar.NewsLetter.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, UUID> {
    boolean existsByEmail(String email);
    List<Subscriber> findAllByActiveTrue();
}
