package com.huisam.springstudy.reactive;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        TcpClient tcpClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .doOnConnected(connection ->
                        connection.addHandler(new ReadTimeoutHandler(3, TimeUnit.SECONDS))
                                .addHandler(new WriteTimeoutHandler(3, TimeUnit.SECONDS))
                );
        ClientHttpConnector connector = new ReactorClientHttpConnector(HttpClient.from(tcpClient));
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .clientConnector(connector)
                .build();
    }
}
