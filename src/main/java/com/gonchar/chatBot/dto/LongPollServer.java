package com.gonchar.chatBot.dto;

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
public class LongPollServer {

    private String key;
    private String server;
    private String ts;

    private ErrorResult error;

    @JsonProperty("response")
    private void unpackNested(Map<String,Object> response) {
        this.key = (String) response.get("key");
        this.server = (String) response.get("server");
        this.ts = (String) response.get("ts");
    }

}
