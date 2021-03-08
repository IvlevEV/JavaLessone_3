package Lessone06.HomeWorkSix_Chat_Fx.server.src.main.java.ru.morou.server;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerConfiguration {
    private Integer port = 4774;
    private Integer threads = 4;
    private String host = "localhost";
}
