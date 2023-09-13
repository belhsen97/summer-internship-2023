package tn.esprit.Listeners;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import tn.esprit.Libs.IObjectMapperConvert;
import tn.esprit.Models.WebsocketClientConnectModel;
import tn.esprit.Models.WebsocketMessageModel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebSocketHandler extends AbstractWebSocketHandler {

    IObjectMapperConvert iObjectMapperConvert;
    public WebSocketHandler (IObjectMapperConvert iObjectMapperConvert){
        this.iObjectMapperConvert  = iObjectMapperConvert;
    }

    //private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
    private List<WebsocketClientConnectModel> listClients = new ArrayList<WebsocketClientConnectModel>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //sessions.add(session);
        listClients.add(WebsocketClientConnectModel.builder().session(session).build());
        System.out.println( "list Clients : "+ listClients.size() );
        System.out.println("New client connected: " + session.getId());
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        System.out.println("handleTextMessage  : "+message.getPayload());
        //session.sendMessage(message);
        //for (WebSocketSession s : sessions){      s.sendMessage(message);}
        for (WebsocketClientConnectModel s : listClients){      s.getSession().sendMessage(message);}
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
        System.out.println("handleBinaryMessage   " );
        final   String str =  this.toString (  message );
          System.out.println(str);
        final  WebsocketMessageModel msg = (WebsocketMessageModel) iObjectMapperConvert.convertToObject(str , WebsocketMessageModel.class);
        if ( msg != null ) {
            if (  msg.getDestinationName() != "" && msg.getDestinationName() != null && msg.getDestinationName().equals("chat-service")) {
                if (msg.getTopic().equals("init")) {
                    listClients.stream()
                            .filter(obj -> obj.getSession() == session)
                            .forEach(obj -> obj.setClientName(msg.getSenderName()));
                }
            }
            if (  msg.getSenderName() != "" && msg.getSenderName() != null) {
                listClients.stream()
                        .filter(obj -> obj.getSession() == session)
                        .forEach(obj -> obj.setClientName(msg.getSenderName()));
            }
            if (  msg.getDestinationName() != "" && msg.getDestinationName() != null && !msg.getDestinationName().equals("chat-service")) {

                listClients.stream()
                        .filter(obj -> obj.getClientName().equals(msg.getDestinationName())  )
                        .forEach(obj -> {
                            try {
                                obj.getSession().sendMessage(message);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }

        }
        //session.sendMessage(message);
        //for (WebSocketSession s : sessions){      s.sendMessage(message);}
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception{
        System.out.println("onError   :"+exception.getMessage() );
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //sessions.remove(session);
        listClients = listClients.stream().filter(obj -> !(obj.getSession() == session)).collect(Collectors.toList());
        System.out.println( "list Clients : "+ listClients.size() );

        System.out.println("afterConnectionClosed : " + status.toString() + " \n session "+ session.getId());
    }


    private String toString ( BinaryMessage message ){
        byte[] binaryData = message.getPayload().array();
        return new String(binaryData, StandardCharsets.UTF_8); }
}