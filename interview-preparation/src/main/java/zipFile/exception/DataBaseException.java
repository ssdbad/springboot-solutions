package zipFile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DataBaseException extends RuntimeException{

	private static final long serialVersionUID = -1256327637477417496L;

	public DataBaseException(String message) {
		super(message);
	}
}
