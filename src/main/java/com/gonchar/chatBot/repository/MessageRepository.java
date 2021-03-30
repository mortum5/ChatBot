package com.gonchar.chatBot.repository;

import com.gonchar.chatBot.dto.LongPollServer;
import com.gonchar.chatBot.dto.Message;
import com.gonchar.chatBot.dto.MessageSendResponse;
import com.gonchar.chatBot.dto.Response;

/**
 *
 *
 * @author Andrey Gonchar
 * @since 0.1
 */
public interface MessageRepository {


    LongPollServer getLongPollServer();

    MessageSendResponse sendMessage(Message message);

    Response getNewMessage(LongPollServer server);
}
