package Lessone06.HomeWorkSix_Chat_Fx.chat.src.main.java.ru.morou.chat.task;

import ru.morou.chat.Client;

import java.util.Scanner;

public class ClientTaskMessageInput extends AbstractChatTask {

    private final static String CMD_EXIT = "exit";

    public ClientTaskMessageInput(final Client client){
        super(client);
    }

    @Override
    public void run(){
        System.out.println("Enter cmd (message or exit)");
        final Scanner in = new Scanner(System.in);
        final String message = in.nextLine();

        if (CMD_EXIT.equals(message)){
            client.exit();
            return;
        }

        client.broadcast(message);
        System.out.println();
        client.run(new ClientTaskMessageInput(client));
    }
}
