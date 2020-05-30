package com.huisam.springstudy.reactive;

import com.huisam.springstudy.mapstruct.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Log4j2
public class WebClientController {

    private final WebClient webClient;

    private final Environment environment;

    @PostMapping(value = "/react",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order react(@RequestBody @Valid OrderRequestBody body) {
        log.info("React name : {}", body);
        final String port = environment.getProperty("local.server.port");

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/orders")
                        .port(port)
                        .build()
                )
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(body), OrderRequestBody.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    System.err.println(clientResponse.logPrefix());
                    return clientResponse.bodyToMono(RuntimeException.class);
                })
                .bodyToMono(Order.class)
                .block();
    }
}
