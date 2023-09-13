package tn.esprit.Websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import tn.esprit.Libs.IObjectMapperConvert;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class WebSocketSessionHolder   {
    @Autowired
    IObjectMapperConvert iObjectMapperConvert;
    private WebSocketSession session = null ;
    public WebSocketSession getSession() { return session;}
    public void setSession(WebSocketSession session) {this.session = session; }
    public void sendbinaryMessage(WebsocketMessageModel websocketMessageModel ) throws IOException, InterruptedException {
        String content =  iObjectMapperConvert.convertToJsonString(websocketMessageModel);
        this.sendbinaryMessage(content);
    }
    public void sendbinaryMessage(String inputString ) throws IOException, InterruptedException {
        BinaryMessage binaryMessage = new BinaryMessage(inputString.getBytes());
        if (this.session == null){ this.waitForConnection( 4000)  ; }
        this.session.sendMessage(binaryMessage);
    }

    public boolean waitForConnection(long CONNECTION_TIMEOUT_MS) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        while ( (  this.session == null) &&  ((System.currentTimeMillis() - startTime) < CONNECTION_TIMEOUT_MS)) {
            Thread.sleep(300); // Sleep for a short interval before checking again
        }
        return   this.session != null;}

    public String convertToString ( BinaryMessage message ){
        byte[] binaryData = message.getPayload().array();
        return new String(binaryData, StandardCharsets.UTF_8); }
}
