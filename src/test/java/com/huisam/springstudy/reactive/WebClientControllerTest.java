package com.huisam.springstudy.reactive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huisam.springstudy.mapstruct.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class WebClientControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Test
    @DisplayName("WebTestClient 테스트")
    void web_test_client_test() throws Exception {
        /* given */
        final String body = objectMapper.writeValueAsString(new OrderRequestBody("hi"));
        /* when */
        final Order order = webTestClient.post()
                .uri("/react")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(body), String.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class)
                .returnResult()
                .getResponseBody();
        /* then */
        assertThat(order).isNotNull();
        assertThat(order.getName()).isEqualTo("hi");
    }
}