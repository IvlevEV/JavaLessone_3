package Lessone06.HomeWorkSix_Chat_Fx.chat.src.main.java.ru.morou.chat.impl;

import lombok.Getter;
import lombok.SneakyThrows;
import ru.morou.chat.ChatConfiguration;
import ru.morou.chat.Client;
import ru.morou.chat.task.AbstractChatTask;
import ru.morou.chat.task.ClientTaskMessageInput;
import ru.morou.chat.task.ClientTaskMessageRead;
import ru.morou.chat.task.ClientTaskMessageSend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

@Getter
public class ChatClientRunner implements Client {

    private ChatConfiguration configuration;
    private ExecutorService executor;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ChatClientRunner(final ChatConfiguration configuration, final ExecutorService executor){
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    @SneakyThrows
    public void run(){
        final String host = configuration.getHost();
        final Integer port = configuration.getPort();
        socket = new Socket(host, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        run(new ClientTaskMessageRead(this));
        run(new ClientTaskMessageInput(this));
    }

    @Override
    public void run(final AbstractChatTask task){
        if(task == null) return;
        executor.submit(task);
    }

    public static void main(String[] args) {
        final ChatClient client = new ChatClient();
        client.run();
    }

    @Override
    public void broadcast(final String message) {
        if(message == null || message.isEmpty()) return;
        run(new ClientTaskMessageSend(this, message));
    }

    @Override
    @SneakyThrows
    public void exit(){
        System.out.println("Chat client disconnected...");
        socket.close();
        System.exit(0);
    }
}
