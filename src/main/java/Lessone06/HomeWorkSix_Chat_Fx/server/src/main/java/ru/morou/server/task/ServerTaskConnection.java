package Lessone06.HomeWorkSix_Chat_Fx.server.src.main.java.ru.morou.server.task;

import lombok.SneakyThrows;
import ru.morou.server.Server;

import java.net.Socket;

public class ServerTaskConnection extends AbstractServerTask {

    public ServerTaskConnection(final Server server) {
        super(server);
    }

    @Override
    @SneakyThrows
    public void run(){
        System.out.println("getServerSocket().accept()");
        final Socket socket = server.getServerSocket().accept();
        server.run(new ServerTaskConnection(server));
        server.run(new ServerTaskMessageRead(server, socket));
        server.add(socket);
    }
}
