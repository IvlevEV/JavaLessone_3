package Lessone06.HomeWorkSix_Chat_Fx.server.src.main.java.ru.morou.server.task;

import ru.morou.server.Server;
import ru.morou.server.model.Connection;

public class ServerTaskMessageBroadcast extends AbstractServerTask {

    private String message;

    public ServerTaskMessageBroadcast(Server server, String message) {
        super(server);
        this.message = message;
    }

    @Override
    public void run(){
        for (final Connection connection: server.connections()) {
            connection.send(message);
        }
    }
}
