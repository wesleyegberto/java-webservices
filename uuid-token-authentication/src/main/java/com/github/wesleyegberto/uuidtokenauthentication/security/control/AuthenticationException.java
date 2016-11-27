package com.github.wesleyegberto.uuidtokenauthentication.security.control;

public class AuthenticationException extends Exception {
	private static final long serialVersionUID = 5677567439874235834L;

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(Throwable cause) {
		super(cause);
	}
}
