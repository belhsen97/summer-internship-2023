package tn.esprit.appRunners;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.ByteArrayBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import tn.esprit.Entitys.Attachment;
import tn.esprit.Entitys.Category;
import tn.esprit.Entitys.User;
import tn.esprit.Libs.IObjectMapperConvert;
import tn.esprit.Repositorys.UserRepository;
import tn.esprit.Services.AttachmentService;
import tn.esprit.Services.IFileService;
import tn.esprit.Services.UserService;
import tn.esprit.Websocket.WebSocketMessageListener;
import tn.esprit.Websocket.WebSocketSessionHolder;
import tn.esprit.Websocket.WebsocketMessageModel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Order(value=1)//Register BeanRunnerOne bean
@Slf4j
@Component
public class bean_ResetData implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {
     //  AA.insert(User.builder().username("belhsen97").build());
     //  JavaWebSocketClient.main("AAAAAAAAAA");
     // JavaxWebSocketClient.main( iFileService.Edit_ConfirmMailPage("belhsen","192"));





       /* MyWebSocketHandler myWebSocketHandler = new MyWebSocketHandler(  );//( thermostat )
        {
            @Override
            public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
                System.out.println("RRRR: " + message.getPayload());
            }
        };
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(
                client,
                myWebSocketHandler, // Your custom WebSocket handler
                "ws://localhost:8055/chat" // Change this to your server URL
        );
        connectionManager.start();

*/




        /*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("tn.esprit.Websocket");
        context.refresh();
        MyWebSocketHandler ms = context.getBean(MyWebSocketHandler.class);
        WebSocketMessageListener mListener = new MessageListenerc();
        ms.addListener(mListener);*/
        /*ms.addListener(new WebSocketMessageListener() {
            @Override
            public void onMessageReceived(WebSocketSession session, WebSocketMessage<?> message) {
                System.out.println("Received message: " + message);
            }
        });*/


      /*  System.out.println( (sessionHolder. waitForConnection(  12000l)
        ? "true" : "false" )
        ) ;*/






       /* WebSocketClient client = new StandardWebSocketClient();
        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(
                client,
                new AbstractWebSocketHandler(){
                    @Override
                    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                        MyWebSocketHandler.session = session;
                        System.out.println("Connected to WebSocket server");
                        String text = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
                        TextMessage message = new TextMessage(text.getBytes());
                        session.sendMessage(message);

                        // You can send initial messages here if needed
                        // session.sendMessage(new TextMessage("Hello, WebSocket server!"));
                    }
                }, // Your custom WebSocket handler
                "ws://localhost:8055/chat" // Change this to your server URL
        );
        connectionManager.start();*/



    //     String text = "HHHHHHHHHHHHHHHHHHHHHHHHHHH";
     //    TextMessage message = new TextMessage(text.getBytes());
//        long startTime = System.currentTimeMillis();
//        while ((sessionHolder.getSession() == null) && (System.currentTimeMillis() - startTime < 10000)) {
//            Thread.sleep(100); // Sleep for a short interval before checking again
//        }
//         // sessionHolder. waitForConnection(  12000l)
      //  Thread.sleep(2000);
        // Convert the String to bytes
        //byte[] inputBytes = inputString.getBytes();
        // Create a ByteArrayBuffer from the bytes
        //ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(inputBytes.length);
        //byteArrayBuffer.append(inputBytes, 0, inputBytes.length);


//        String msg = "{\"subject\":\"Forgot Password\",\"email\":\"belhsenbachouch97@gmail.com\",";
//        String file =  iFileService.Edit_forgotPasswordPage( "bachouch97", "code" );
//        msg += "\"bodyContents\":[{\"text/plain\",\"body\"},{\"text/html\",\""+file+"\"}]";
//        msg += "}";
//        WebsocketMessageModel websocketMessageModel = WebsocketMessageModel.builder()
//                .destinationApplicationName("mail-service")
//                .data(msg)
//                .build();
//        String content =  iObjectMapperConvert.convertToJsonString(websocketMessageModel);
//        System.out.println(content.length());
        //sessionHolder.sendbinaryMessage(content);
    }


@Autowired
  private UserService AA ;
   @Autowired
    private IFileService iFileService;
   private   final WebSocketSessionHolder sessionHolder;

    @Autowired
    public  bean_ResetData (  WebSocketSessionHolder sessionHolder  ) {


        System.out.println("bean_ResetData" );
      this.sessionHolder = sessionHolder;



    }
    @Autowired
    IObjectMapperConvert iObjectMapperConvert;
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.scan("tn.esprit.Websocket");
//        context.refresh();
//        MyWebSocketHandler ms = context.getBean(MyWebSocketHandler.class);
//        context.close();

}





