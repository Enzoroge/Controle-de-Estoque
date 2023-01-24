package com.example.estoque.api.service;

public class ControllerrNotFoundException extends Exception {
	
	
	
	private static final long serialVersionUID = 1L;

	public ControllerrNotFoundException(Object id) {
		super("Controller not found. Id " + id);
	}

}
