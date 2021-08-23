package zipFile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3481686469122683337L;
	
	public UserNotFoundException(String message) {
		super(message);
	}
}