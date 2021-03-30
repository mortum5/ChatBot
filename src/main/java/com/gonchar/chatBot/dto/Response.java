package com.gonchar.chatBot.dto;

import lombok.Data;

/**
 *
 *
 * @author Andrey Gonchar
 * @since 0.1
 */
@Data
public class Response {

    private String ts;
    private Updates[] updates;
    private Integer failed;
    private ErrorResult error;

}
