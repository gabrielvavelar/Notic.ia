package io.github.gabrielvavelar.Noticia.controller;

import io.github.gabrielvavelar.Noticia.dto.SubscriberRequestDto;
import io.github.gabrielvavelar.Noticia.dto.SubscriberResponseDto;
import io.github.gabrielvavelar.Noticia.exception.InvalidUnsubscribeTokenException;
import io.github.gabrielvavelar.Noticia.service.subscriber.SubscriberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;



import java.util.UUID;

@WebMvcTest(SubscriberController.class)
class SubscriberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SubscriberService subscriberService;

    @Test
    void shouldSubscribe() throws Exception {
        SubscriberRequestDto request =
                new SubscriberRequestDto("subscriber@email.com");

        SubscriberResponseDto response =
                new SubscriberResponseDto(
                        UUID.randomUUID(),
                        "subscriber@email.com",
                        UUID.randomUUID()
                );

        Mockito.when(subscriberService.subscribe(Mockito.any()))
                .thenReturn(response);

        mockMvc.perform(post("/subscribe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value(request.email()))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void shouldUnsubscribe() throws Exception {
        UUID token = UUID.randomUUID();

        Mockito.doNothing()
                .when(subscriberService).unsubscribe(token);

        mockMvc.perform(delete("/unsubscribe")
                        .param("token", token.toString()))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundWhenTokenDoesNotExist() throws Exception {
        UUID token = UUID.randomUUID();

        Mockito.doThrow(new InvalidUnsubscribeTokenException("Invalid unsubscribe token"))
                .when(subscriberService).unsubscribe(token);

        mockMvc.perform(delete("/unsubscribe")
                        .param("token", token.toString()))
                .andExpect(status().isNotFound());
    }
}

