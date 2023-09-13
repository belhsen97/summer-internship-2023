package tn.esprit.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.FOUND )
public class RessourceFoundException extends RuntimeException{
    public RessourceFoundException(String message){
        super (message);
    }
}