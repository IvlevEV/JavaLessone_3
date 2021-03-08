package Lessone06.HomeWorkSix_Chat_Fx.server.src.main.java.ru.morou.server.service;


import ru.morou.server.Server;
import ru.morou.server.model.Connection;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ConnectionServiceBean implements ConnectionService {

    private final Server server;
    private final List<Connection> connections = new ArrayList<>();

    public ConnectionServiceBean(final Server server) {
        this.server = server;
    }

    public List<Connection> connections() {
        return connections;
    }

    public void add(final Socket socket) {
        if (socket == null) return;
        final Connection connection = new Connection(server, socket);
        connections.add(connection);
        System.out.println("Added connection with id = " + connection.getId() + "...");
    }

    public Connection get(final Socket socket){
        if(socket == null) return null;
        for (final Connection connection: connections){
            if(connection.getSocket().equals(socket)) return connection;
        }
        return null;
    }

    public void remove(final Socket socket){
        if (socket == null) return;
        final Connection connection = get(socket);
        connections.remove(connection);
        System.out.println("Removed connection with id = " + connection.getId() + "...");
    }
}
