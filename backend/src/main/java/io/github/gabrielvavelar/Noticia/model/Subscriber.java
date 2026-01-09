package io.github.gabrielvavelar.Noticia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String email;
    private UUID unsubscribeToken;

    public Subscriber() {}
    public Subscriber(String email) {this.email = email;}
}
