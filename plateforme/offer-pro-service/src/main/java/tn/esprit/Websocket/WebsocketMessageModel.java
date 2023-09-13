package tn.esprit.Websocket;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebsocketMessageModel {
    String  topic;
    String  senderName ;
    String  data;
    String  destinationName ;
    String  status;
}
