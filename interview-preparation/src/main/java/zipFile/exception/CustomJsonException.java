package zipFile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomJsonException extends RuntimeException{

	private static final long serialVersionUID = 4923738620842199079L;
	
	public CustomJsonException(String message) {
		super(message);
	}
}