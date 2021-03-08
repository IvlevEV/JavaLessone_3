package Lessone06.HomeWorkSix_Chat_Fx.server.src.main.java.ru.morou.server.model;

import lombok.Getter;
import lombok.SneakyThrows;
import ru.morou.server.Server;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.UUID;

@Getter
public class Connection {

    private final String id = UUID.randomUUID().toString();
    private final Socket socket;
    private final Server server;


    public Connection(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @SneakyThrows
    public void send(final String message){
        final DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
        stream.writeUTF(message);
    }
}
