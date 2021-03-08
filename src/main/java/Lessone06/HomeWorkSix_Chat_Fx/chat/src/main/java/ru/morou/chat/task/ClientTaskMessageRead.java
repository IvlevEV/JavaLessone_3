package Lessone06.HomeWorkSix_Chat_Fx.chat.src.main.java.ru.morou.chat.task;

import lombok.Getter;
import ru.morou.chat.Client;

import java.io.IOException;

@Getter
public class ClientTaskMessageRead extends AbstractChatTask {
    public ClientTaskMessageRead(final Client client){
        super(client);
    }

    @Override
    public void run(){
        try {
            final String message = client.getIn().readUTF();
            System.out.println("*** " + message + " ***");
            client.run(new ClientTaskMessageRead(client));
        } catch (IOException e){
            e.printStackTrace();
            client.exit();
        }
    }

}
