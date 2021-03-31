package com.gonchar.chatBot.service;

import com.gonchar.chatBot.dto.*;
import com.gonchar.chatBot.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 *
 *
 * @author Andrey Gonchar
 * @since 0.1
 */
@Service
public class ChatBotServiceImpl implements ChatBotService {

    @Autowired
    private MessageRepository repo;

    private static final Logger logger = LoggerFactory.getLogger(ChatBotServiceImpl.class);

    public void start() {
        getServer();
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
