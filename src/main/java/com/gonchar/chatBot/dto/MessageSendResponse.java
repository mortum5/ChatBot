package com.gonchar.chatBot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 *
 * @author Andrey Gonchar
 * @since 0.1
 */
@Data
public class MessageSendResponse {

    @JsonProperty(value = "response")
    Long messageId;

    ErrorResult error;


}
