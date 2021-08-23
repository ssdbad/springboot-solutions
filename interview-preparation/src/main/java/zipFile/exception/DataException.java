package zipFile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataException extends RuntimeException{

	private static final long serialVersionUID = 4205842823269914894L;
	
	public DataException(String message) {
		super(message);
	}
}