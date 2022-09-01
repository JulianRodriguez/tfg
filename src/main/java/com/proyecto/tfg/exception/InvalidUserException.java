package com.proyecto.tfg.exception;

public class InvalidUserException extends Exception {

	private static final long serialVersionUID = 18985678L;
	
	private static final String MSG = "El usuario o contraseña son incorrectos";
	
	public InvalidUserException() {
		super(MSG);
	}
	
	public InvalidUserException(String message) {
		super(message);
	}

}
