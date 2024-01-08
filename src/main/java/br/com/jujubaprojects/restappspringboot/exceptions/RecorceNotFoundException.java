package br.com.jujubaprojects.restappspringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecorceNotFoundException extends RuntimeException{
    
      private static final long SerializableUID = 1L;
    
    public RecorceNotFoundException(String string) {
        super();
    }

  
}
