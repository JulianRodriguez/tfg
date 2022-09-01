package com.proyecto.tfg.exception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1689769876L;

	public static final String MSG = "Esta entiedad no existe";
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException() {
		super(MSG);
}
}
