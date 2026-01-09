package io.github.gabrielvavelar.Noticia.repository;

import io.github.gabrielvavelar.Noticia.model.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class SubscriberRepositoryTest {
    @Autowired
    private SubscriberRepository subscriberRepository;
    private Subscriber subscriber;

    @BeforeEach
    void setUp(){
        subscriber = new Subscriber("subscriber1@email.com");
        subscriberRepository.save(subscriber);
    }

    @Test
    void shouldExistsByEmail(){
        assertTrue(subscriberRepository.existsByEmail("subscriber1@email.com"));
    }

    @Test
    void shouldNotExistsByEmail(){
        assertFalse(subscriberRepository.existsByEmail("subscriber2@email.com"));
    }

    @Test
    void shouldFindByUnsubscribeToken(){
        Subscriber result = subscriberRepository
                .findByUnsubscribeToken(subscriber.getUnsubscribeToken())
                .orElseThrow();

        assertEquals(subscriber.getEmail(), result.getEmail());
    }

    @Test
    void shouldNotFindByUnsubscribeToken(){
        assertTrue(
                subscriberRepository.findByUnsubscribeToken(UUID.randomUUID())
                        .isEmpty()
        );
    }
}
