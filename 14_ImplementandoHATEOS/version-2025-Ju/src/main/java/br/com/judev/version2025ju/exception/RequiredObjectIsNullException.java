package br.com.judev.version2025ju.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException{
    public RequiredObjectIsNullException(){
        super("It is not Allowed to Persist a null object");
    }

    public RequiredObjectIsNullException(String message){
        super(message);
    }
}
