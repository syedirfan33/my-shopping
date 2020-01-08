/**
 * 
 */
package com.shopping.my.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Syed Irfan
 *
 */
@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
public class UnAuthorizeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnAuthorizeException() {
		super();
	}

	public UnAuthorizeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnAuthorizeException(String message) {
		super(message);
	}

	public UnAuthorizeException(Throwable cause) {
		super(cause);
	}

}
