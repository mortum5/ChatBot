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
public class ErrorResult {

        @JsonProperty(value = "error_code")
        private Long errorCode;
        @JsonProperty(value = "error_msg")
        private String errorMsg;

}
