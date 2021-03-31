package com.gonchar.chatBot;

import com.gonchar.chatBot.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    ChatBotService chatBot;

    public static void main(String[] args) {
        SpringApplication.run(ChatBotApplication.class, args);
    }

    @Override
    public void run(String... args) {

        chatBot.start();
        exit(0);
    }



}
