package br.com.judev.version2025ju.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnsupportedMathOperationException extends RuntimeException{
    public UnsupportedMathOperationException(String message) {
        super(message);
    }
}
