package tn.esprit.Websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.ArrayList;
import java.util.List;

@Configuration("WebSocketConfig")
@EnableWebSocket
public class WebSocketConfig {
    @Bean
    public WebSocketConnectionManager connectionManager(List<WebSocketMessageListener> listeners) {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(
                client,
                new WebSocketHandler(listeners,sessionHolder ),
                "ws://localhost:8055/chat"
        );
        connectionManager.setAutoStartup(true);//   connectionManager.start();
        return connectionManager;
    }
    @Autowired
    private WebSocketSessionHolder sessionHolder;
    @Bean
    public List<WebSocketMessageListener> webSocketMessageListeners() {
        return new ArrayList<>();
    }
    //@Autowired
    //private List<WebSocketMessageListener> listeners = new ArrayList<WebSocketMessageListener>();  ;
}
