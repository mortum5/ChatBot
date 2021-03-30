package com.gonchar.chatBot;

import com.gonchar.chatBot.dto.*;
import com.gonchar.chatBot.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

import static java.lang.System.exit;

/**
 *
 *
 * @author Andrey Gonchar
 * @since 0.1
 */
@SpringBootApplication
public class ChatBotApplication implements CommandLineRunner {

    @Autowired
    private MessageRepository repo;

    private static final Logger logger = LoggerFactory.getLogger(ChatBotApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ChatBotApplication.class, args);
    }

    @Override
    public void run(String... args) {

        getServer();

        exit(0);
    }

    private void getServer() {
        LongPollServer longPollServer = repo.getLongPollServer();
        ErrorResult err = Objects.requireNonNull(longPollServer).getError();
        if (err != null && err.getErrorCode() != null) {
            logger.error("Request: {} {}", err.getErrorCode(), err.getErrorMsg());
        } else {
            getMessage(longPollServer);
        }

    }

    private void getMessage(LongPollServer longPollServer) {
        Response response = repo.getNewMessage(longPollServer);
        Updates[] updates = response.getUpdates();
        if (updates.length == 0) {
            getMessage(longPollServer);
        }
        Message msg = updates[0].getObject();
        ErrorResult err = Objects.requireNonNull(response).getError();
        if (err != null && err.getErrorCode() != null) {
            logger.error("Request: {} {}", err.getErrorCode(), err.getErrorMsg());
        } else if (response.getFailed() != null) {
            getServer();
        } else {
            longPollServer.setTs(response.getTs());
            sendMessage(msg, longPollServer);
        }
    }

    private void sendMessage(Message msg, LongPollServer server) {
        MessageSendResponse resp = repo.sendMessage(msg);
        ErrorResult err = Objects.requireNonNull(resp).getError();
        if (err != null && err.getErrorCode() != null) {
            logger.error("Request: {} {}", err.getErrorCode(), err.getErrorMsg());
        } else {
            getMessage(server);
        }
    }

}
