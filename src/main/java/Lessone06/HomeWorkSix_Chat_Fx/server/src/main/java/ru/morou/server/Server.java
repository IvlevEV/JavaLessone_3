package Lessone06.HomeWorkSix_Chat_Fx.server.src.main.java.ru.morou.server;

import ru.morou.server.model.Connection;
import ru.morou.server.task.AbstractServerTask;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;

public interface Server extends Runnable {
    ExecutorService getExecutor();
    ServerConfiguration getConfiguration();
    ServerSocket getServerSocket();
    List<Connection> connections();
    void run();
    void run(AbstractServerTask task);
    void add(Socket socket);
    void remove(Socket socket);
}
