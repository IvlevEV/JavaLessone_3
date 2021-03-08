package Lessone06.HomeWorkSix_Chat_Fx.server.src.main.java.ru.morou.server.api;

import ru.morou.server.ServerConfiguration;

import java.util.concurrent.ExecutorService;

public interface ServerApp extends Runnable {
    ExecutorService getExecutor();
    ServerConfiguration getConfiguration();
    void run();
}
