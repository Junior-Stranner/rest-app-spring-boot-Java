package br.com.judev.version2025ju.exception.handler;

import br.com.judev.version2025ju.exception.ExceptionResponse;
import br.com.judev.version2025ju.exception.RequiredObjectIsNullException;
import br.com.judev.version2025ju.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice// sempre em Exeções de controllers e ninguem fornça um tratamento global , o ControllerAdvice faz
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse responde = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(responde, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request){
        ExceptionResponse responde = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(responde, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public final ResponseEntity<ExceptionResponse> handleUnsupportedOperationException(
            UnsupportedOperationException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequiredObjectIsNullException.class)
    public final ResponseEntity<ExceptionResponse> RequiredObjectIsNullException(
            UnsupportedOperationException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
