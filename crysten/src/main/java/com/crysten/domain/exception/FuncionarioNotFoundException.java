package com.crysten.domain.exception;

public class FuncionarioNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public FuncionarioNotFoundException(String mensagem) {
		super(mensagem);
	}
	
}