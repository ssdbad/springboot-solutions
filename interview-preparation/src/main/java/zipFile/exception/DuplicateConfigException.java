package zipFile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateConfigException extends RuntimeException{

	private static final long serialVersionUID = 2788780161329033463L;
	
	public DuplicateConfigException(String message) {
		super(message);
	}
}
