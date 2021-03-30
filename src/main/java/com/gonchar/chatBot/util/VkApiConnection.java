package com.gonchar.chatBot.util;

import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

/**
 *
 *
 * @author Andrey Gonchar
 * @since 0.1
 */
public interface VkApiConnection {

    WebClient.ResponseSpec get(URI uri);
}
