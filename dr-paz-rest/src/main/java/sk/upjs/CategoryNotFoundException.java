package sk.upjs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -6806120645998269030L;
	
	public CategoryNotFoundException() {
		super();
	}
	public CategoryNotFoundException(String message) {
		super(message);
	}
	
}
