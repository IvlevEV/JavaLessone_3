package Lessone06.HomeWorkSix_Chat_Fx.chat.src.main.java.ru.morou.chat.task;

import ru.morou.chat.Client;

public abstract class AbstractChatTask extends Thread {
    protected Client client;
    protected AbstractChatTask(Client client) {
        this.client = client;
    }
}
