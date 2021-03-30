package com.gonchar.chatBot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

/**
 *
 *
 * @author Andrey Gonchar
 * @since 0.1
 */
@Component
public class VkApiConnectionImpl implements VkApiConnection {

    private final WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(VkApiConnectionImpl.class);

    public VkApiConnectionImpl() {
        this.webClient = WebClient.builder()
                .filter(logRequest())
                .build();
    }

    @Override
    public WebClient.ResponseSpec get(URI uri) {
        return this.webClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();

    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            return next.exchange(clientRequest);
        };
    }



}
