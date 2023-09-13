package tn.esprit.appRunners;

import tn.esprit.Services.IFileService;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@ClientEndpoint
public class JavaxWebSocketClient {
    private static final int CHUNK_SIZE = 1024; // Adjust the chunk size as needed

    private IFileService iFileService;
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket  onOpen Connected to server");
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("WebSocket onMessage");
        System.out.println("Received message: " + message);
    }

//@OnMessage
//public void onMessage(Session session, String message, boolean last) {
//    // Process the received chunk of data
//    if (last) {
//        System.out.println("Received message: " + message);
//    } else {
//        System.out.println("Received message: " + message);
//    }
//}


    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("WebSocket connection closed: " + reason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("WebSocket error: " + throwable.getMessage());
    }



    public static void main(String str) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            String uri = "ws://localhost:8055/chat";
            Session session = container.connectToServer(JavaxWebSocketClient.class, new URI(uri));


            //session.getBasicRemote().sendText(str);
            byte[] byteArray = str.getBytes(StandardCharsets.UTF_8);

            // Wrap the byte array in a ByteBuffer
            ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
            session.getBasicRemote().sendBinary(byteBuffer);

            // Split the large string into chunks and send each chunk as a message
            /*for (int i = 0; i < str.length(); i += CHUNK_SIZE) {
                int endIndex = Math.min(i + CHUNK_SIZE, str.length());
                String chunk = str.substring(i, endIndex);
                session.getBasicRemote().sendText(chunk);
            }*/
        }

        catch (DeploymentException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
