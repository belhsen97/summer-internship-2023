package tn.esprit.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus ( value = HttpStatus.INTERNAL_SERVER_ERROR )
public class MessagingException extends RuntimeException{
    public MessagingException(String message){
        super (message);
    }
}
