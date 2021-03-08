package Lessone06.HomeWorkSix_Chat_Fx.server.src.main.java.ru.morou.server.task;

import ru.morou.server.Server;

public abstract class AbstractServerTask extends Thread {
    protected final Server server;
    protected AbstractServerTask(Server server){
        this.server = server;
    }
}
