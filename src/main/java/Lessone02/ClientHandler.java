package Lessone02;

import java.io.IOException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientHandler {
    private Server server;
    private String nick;
    private Channel channel;
    private Socket socket;

    public ClientHandler(Socket socket, Server server) {
        this.server = server;
        this.socket = socket;

        try {
            channel = ChannelBase.of(socket);
            Server.getExecutorService().execute(() -> {
                auth();
                System.out.println(nick + " handler waiting for new massages");
                while (socket.isConnected()) {
                    Message msg = channel.getMessage();
                    if (msg == null) continue;
                    switch (msg.getType()) {
                        case EXIT_COMMAND:
                            server.unsubscribe(this);
                            break;
                        case PRIVATE_MESSAGE:
                            sendPrivateMessage(msg.getBody());
                            break;
                        case BROADCAST_CHAT:
                            server.sendBroadcastMessage(nick + " : " + msg.getBody());
                            break;
                        case CHANGE_NICK:
                            Pattern pattern = Pattern.compile("[^\\s]{1,15}");
                            Matcher matcher = pattern.matcher(msg.getBody());
                            if (matcher.find()) {
                                if (server.getAuthService().changeNick(this.nick, matcher.group(0))) {
                                    this.nick = matcher.group(0);
                                    channel.sendMessage("Ваш ник успешно изменён на " +
                                            matcher.group(0) );
                                }else
                                    channel.sendMessage("Не удалось сменит ник. " +
                                            "Возможно такой ник уже занят");
                            }
                            break;
                        default:
                            System.out.println("invalid message type");
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPrivateMessage(String messageWithNickTo) {
        int firstSpaceIndex = messageWithNickTo.indexOf(" ");
        final String nickTo = messageWithNickTo.substring(0, firstSpaceIndex);
        final String message = messageWithNickTo.substring(firstSpaceIndex).trim();
        if (server.isNickTaken(nickTo)) {
            server.sendPrivateMessage(nick, nickTo, nick + " -> " + nickTo + " : " + message);
        } else {
            sendMessage(nickTo + "не принято!");
        }
    }


    private void auth() {
        while (true) {
            TimeoutChecker.set(this);
            if (!channel.hasNextLine()) break;
            Message message = channel.getMessage();
            if (MessageType.AUTH_MESSAGE.equals(message.getType())) {
                String[] commands = message.getBody().split(" ");// /auth login1 pass1
                if (commands.length >= 2) {
                    String login = commands[0];
                    String password = commands[1];
                    System.out.println("Попытка входа с лонина : " + login + " и пароля : " + password);
                    String nick = server.getAuthService()
                            .authByLoginAndPassword(login, password);
                    if (nick == null) {
                        String msg = "Неправильный логин или пароль";
                        System.out.println(msg);
                        channel.sendMessage(msg);
                    } else if (server.isNickTaken(nick)) {
                        String msg = "Ник : " + nick + " уже занят";
                        System.out.println(msg);
                        channel.sendMessage(msg);
                    } else {
                        this.nick = nick;
                        String msg = "Всё супер";
                        TimeoutChecker.unset(this);
                        System.out.println(msg);
                        channel.sendMessage(msg);
                        server.subscribe(this);
                        break;
                    }
                }
            } else {
                channel.sendMessage("Что то совсем не так");
            }
        }
    }

    public void sendMessage(String msg) {
        channel.sendMessage(msg);
    }

    public String getNick() {
        return nick;
    }

    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}