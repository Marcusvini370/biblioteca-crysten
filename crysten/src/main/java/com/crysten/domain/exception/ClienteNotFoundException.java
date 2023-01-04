package com.crysten.domain.exception;

public class ClienteNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public ClienteNotFoundException(String mensagem) {
		super(mensagem);
	}
	
}