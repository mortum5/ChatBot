package com.gonchar.chatBot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 *
 *
 * @author Andrey Gonchar
 * @since 0.1
 */
@Data
public class Message {
    @JsonIgnore
    private String peerId;
    @JsonIgnore
    private String message;

    @JsonProperty("message")
    private void unpackNested(Map<String, Object> message) {
        this.peerId = message.get("peer_id").toString();
        this.message = message.get("text").toString();
    }


}
