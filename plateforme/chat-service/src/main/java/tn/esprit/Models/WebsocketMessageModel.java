package tn.esprit.Models;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebsocketMessageModel {
    String  topic;
    @Value("${spring.application.name}")
    String  senderName ;
    String  data;
    String  destinationName;
    String  status;
}
