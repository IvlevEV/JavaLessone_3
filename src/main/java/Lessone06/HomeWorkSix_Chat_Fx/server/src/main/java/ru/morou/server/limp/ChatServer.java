package Lessone06.HomeWorkSix_Chat_Fx.server.src.main.java.ru.morou.server.limp;

import lombok.Getter;
import ru.morou.server.Server;
import ru.morou.server.ServerConfiguration;
import ru.morou.server.api.ServerApp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class ChatServer implements ServerApp {

    private final ServerConfiguration configuration;
    private final ExecutorService executor;
    private final Server server;

    public ChatServer() {
        configuration = new ServerConfiguration();
        executor = Executors.newCachedThreadPool();
        server = new ChatServerRunner(configuration, executor);
    }

    @Override
    public void run() {
        server.run();
    }
}
