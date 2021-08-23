package zipFile.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRespondEntityException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex,	WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateConfigException.class)
	public final ResponseEntity<ExceptionResponse> handleDuplicateConfigException(DuplicateConfigException ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false), HttpStatus.CONFLICT.value());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(DataBaseException.class)
	public final ResponseEntity<ExceptionResponse> handleDataBaseException(DataBaseException ex, WebRequest request){
		ExceptionResponse exceptionResponse = new  ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request){
		Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), errors.toString(), ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataException.class)
	public final ResponseEntity<ExceptionResponse> handleDataNotFoundException(DataException ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage() ,request.getDescription(false) , HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomJsonException.class)
	public final ResponseEntity<ExceptionResponse> handleJsonExecption(CustomJsonException ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
