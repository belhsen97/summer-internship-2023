package tn.esprit.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.FOUND )
public class MultiRessourceFoundException extends RuntimeException{
    public MultiRessourceFoundException(String message){
        super (message);
    }
}