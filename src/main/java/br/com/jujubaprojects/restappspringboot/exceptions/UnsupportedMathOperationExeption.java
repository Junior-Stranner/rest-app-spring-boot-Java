package br.com.jujubaprojects.restappspringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationExeption extends RuntimeException{
    
    
    public UnsupportedMathOperationExeption(String string) {
        super();
    }

    private static final long SerializableUID = 1L;
}
