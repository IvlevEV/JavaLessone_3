package Lessone06.HomeWorkSix_Chat_Fx.chat.src.main.java.ru.morou.chat.impl;

import lombok.Getter;
import ru.morou.chat.ChatConfiguration;
import ru.morou.chat.Client;
import ru.morou.chat.api.ChatApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class ChatClient implements ChatApp {

    private ChatConfiguration configuration;
    private ExecutorService executor;
    private final Client client;

    public ChatClient() {
        configuration = new ChatConfiguration();
        executor = Executors.newCachedThreadPool();
//        executor = Executors.newFixedThreadPool(configuration.getThreads());
        client = new ChatClientRunner(configuration, executor);
    }

    @Override
    public void run(){
        client.run();
    }
}
