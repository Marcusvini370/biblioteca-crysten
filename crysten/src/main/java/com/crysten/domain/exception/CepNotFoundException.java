package com.crysten.domain.exception;

public class CepNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public CepNotFoundException(String mensagem) {
		super(mensagem);
	}
	
}