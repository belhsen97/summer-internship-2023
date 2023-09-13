package tn.esprit.appRunners;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.WebSocketSession;
import java.net.URI;
//@Component
public class MyWebSocketClient {

   /* @Bean
    public WebSocketClient webSocketClient() {
        return new StandardWebSocketClient();
    }

    public void connectToServer() {
        WebSocketClient client = webSocketClient();
        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(
                client,
                new MyWebSocketHandler(), // Your custom WebSocket handler
                "ws://localhost:8080/chat" // Change this to your server URL
        );
        connectionManager.start();
    }*/
}
