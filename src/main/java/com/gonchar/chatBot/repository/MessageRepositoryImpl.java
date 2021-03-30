package com.gonchar.chatBot.repository;

import com.gonchar.chatBot.config.VkApiConfigurationProperties;
import com.gonchar.chatBot.dto.LongPollServer;
import com.gonchar.chatBot.dto.Message;
import com.gonchar.chatBot.dto.MessageSendResponse;
import com.gonchar.chatBot.dto.Response;
import com.gonchar.chatBot.util.VkApiConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

/**
 *
 *
 * @author Andrey Gonchar
 * @since 0.1
 */
@Repository
public class MessageRepositoryImpl implements MessageRepository{

    private final String hostApiVk = "https://api.vk.com/method/";
    private final String getServer = "groups.getLongPollServer";
    private final String sendMessage = "messages.send";

    private UriBuilder uriBuilder;
    @Autowired
    private VkApiConnection connection;
    @Autowired
    private VkApiConfigurationProperties vkApiConfigurationProperties;

    @Override
    public LongPollServer getLongPollServer() {
        uriBuilder = uriConstructor(hostApiVk, getServer, State.GET_LONG_POLL);
        return connection.get(uriBuilder.build(vkApiConfigurationProperties.getProperties()))
                .bodyToMono(LongPollServer.class)
                .block();
    }

    @Override
    public MessageSendResponse sendMessage(Message message) {
        uriBuilder = uriConstructor(hostApiVk, sendMessage, State.SEND_MESSAGE);
        uriBuilder.queryParam("user_id", message.getUserId());
        uriBuilder.queryParam("message", "Вы сказали: " + message.getMessage());
        uriBuilder.queryParam("random_id", 0);
        return connection.get(uriBuilder.build(vkApiConfigurationProperties.getProperties()))
                .bodyToMono(MessageSendResponse.class)
                .block();
    }

    @Override
    public Response getNewMessage(LongPollServer server) {
        uriBuilder = uriConstructor(server.getServer(), "", State.GET_MESSAGES);
        uriBuilder.queryParam("key", server.getKey());
        uriBuilder.queryParam("ts", server.getTs());
        return connection.get(uriBuilder.build())
                .bodyToMono(Response.class)
                .block();

    }

    private static UriBuilder uriConstructor(String server, String path, State state) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(server);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.TEMPLATE_AND_VALUES);
        UriBuilder uriBuilder;
        switch (state) {
            case GET_LONG_POLL:

            case SEND_MESSAGE:
                uriBuilder = factory.uriString("?group_id={group_id}&access_token={access_token}&v={v}");
                break;

            case GET_MESSAGES:
                uriBuilder = factory.uriString("?act=a_check&wait=25");
                break;

            default:
                uriBuilder = factory.builder();
        }
        uriBuilder.path(path);


        return uriBuilder;
    }

    public enum State {
        GET_LONG_POLL, SEND_MESSAGE, GET_MESSAGES
    }
}
