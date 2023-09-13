package tn.esprit.Controller;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import tn.esprit.Libs.IObjectMapperConvert;
import tn.esprit.Models.Msg;
import tn.esprit.Services.IsmtpMailService;
import tn.esprit.Websocket.WebSocketMessageListener;
import tn.esprit.Websocket.WebSocketSessionHolder;
import tn.esprit.Websocket.WebsocketMessageModel;

import java.nio.charset.StandardCharsets;


@Component
public class MailWebsocket implements WebSocketMessageListener {
    private final WebSocketSessionHolder sessionHolder;
    private  final IsmtpMailService mailSender;
    private final IObjectMapperConvert iObjectMapperConvert;
    public  MailWebsocket (WebSocketSessionHolder sessionHolder,
                           IObjectMapperConvert iObjectMapperConvert,
                           IsmtpMailService mailSender) {
        this. sessionHolder = sessionHolder;
        this. iObjectMapperConvert = iObjectMapperConvert;
        this. mailSender = mailSender;
    }
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        final String  str = sessionHolder.convertToString( message);
        System.out.println("Received binary data as string: " + sessionHolder.convertToString( message));
        final  WebsocketMessageModel msg_ws = (WebsocketMessageModel) iObjectMapperConvert.convertToObject(str , WebsocketMessageModel.class);
        if ( msg_ws != null ) {
            if (msg_ws.getTopic().equals("mail-with-multi-body-content")) {
                final  Msg msg = (Msg) iObjectMapperConvert.convertToObject(msg_ws.getData() , Msg.class);
                mailSender.connect();
                mailSender.sendingMultiBodyContent(msg);
            }
        }
    }
}
