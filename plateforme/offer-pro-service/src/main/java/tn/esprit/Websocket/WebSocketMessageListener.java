package tn.esprit.Websocket;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;



public interface WebSocketMessageListener {
    void handleTextMessage(WebSocketSession session, TextMessage message)throws Exception;
    void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception;
}
