package br.com.jujubaprojects.restappspringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RecorceNotFoundException extends RuntimeException{
    
      private static final long SerializableUID = 1L;
    
    public RecorceNotFoundException(String ex) {
        super(ex);
    }
    
    public RecorceNotFoundException() {
        super("It is not allowed to persist a null object");
    }

  
}
