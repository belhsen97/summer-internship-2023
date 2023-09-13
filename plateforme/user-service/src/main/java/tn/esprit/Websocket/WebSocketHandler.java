package tn.esprit.Websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class WebSocketHandler extends AbstractWebSocketHandler   {
    private final WebSocketSessionHolder sessionHolder;
    private List<WebSocketMessageListener> listeners = new ArrayList<>();




    public void addListener(WebSocketMessageListener listener) {listeners.add(listener);}
    public void removeListener(WebSocketMessageListener listener) {listeners.remove(listener);  }
    @Autowired
    public WebSocketHandler(List<WebSocketMessageListener> listeners,WebSocketSessionHolder sessionHolder  ) {
        this.sessionHolder = sessionHolder;
        this.listeners = listeners;
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("websocket - client  Connection Established");
        session.setTextMessageSizeLimit(1024 * 1024);
        session.setBinaryMessageSizeLimit(1024 * 1024);
        sessionHolder.setSession(session);


        sessionHolder.sendbinaryMessage(WebsocketMessageModel.builder()
                                                             .topic("init")
                                                             .senderName("user-service")
                                                             .destinationName("chat-service")
                                                             .build());

        // session.sendMessage(new TextMessage("Hello, WebSocket server!"));
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("websocket - client  handleTextMessage");
        System.out.println( "Size " +listeners.size() );
        for (WebSocketMessageListener listener : listeners) {listener.handleTextMessage(session, message);}
    }
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws  Exception,IOException {
        System.out.println("websocket - client  handleBinaryMessage");
        System.out.println( "Size " +listeners.size() );
//        byte[] binaryData = message.getPayload().array();
//        String receivedString = new String(binaryData, StandardCharsets.UTF_8);
//        System.out.println("Received binary data as string: " + receivedString);
//        session.sendMessage(message);
        for (WebSocketMessageListener listener : listeners) {listener.handleBinaryMessage(session, message);}
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.err.println("websocket - client   error: " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("websocket - client   connection closed: " + closeStatus );

        sessionHolder.setSession(null);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
