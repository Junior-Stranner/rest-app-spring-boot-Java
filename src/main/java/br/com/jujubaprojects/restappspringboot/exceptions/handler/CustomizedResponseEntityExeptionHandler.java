package br.com.jujubaprojects.restappspringboot.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.jujubaprojects.restappspringboot.exceptions.ExeptionsResponse;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExeptionHandler<ExceptionResponse> extends ResponseEntityExceptionHandler{
    
    public final ResponseEntity<ExeptionsResponse> handlAllExceptions(Exception ex, WebRequest request){

        ExeptionsResponse exceptionResponse = new ExeptionsResponse(new Date(),ex.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UnsupportedOperationException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExcptions(Exception ex, WebRequest request) {
		
        ExeptionsResponse exceptionResponse = new ExeptionsResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return null;
		
	//	return new ResponseEntity<>(exceptionResponse, HttpStatus.Not_Found);
	}
  }
