package Lessone06.HomeWorkSix_Chat_Fx.server.src.main.java.ru.morou.server.task;

import ru.morou.server.Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerTaskMessageRead extends AbstractServerTask{

    private Socket socket;

    public ServerTaskMessageRead(final Server server, final Socket socket) {
        super(server);
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            final DataInputStream in = new DataInputStream(socket.getInputStream());
            final String message = in.readUTF();
            System.out.println(message);
            server.run(new ServerTaskMessageRead(server, socket));
            server.run(new ServerTaskMessageBroadcast(server, message));
        } catch (IOException e) {
//            e.printStackTrace();
            server.remove(socket);
        }
    }
}
