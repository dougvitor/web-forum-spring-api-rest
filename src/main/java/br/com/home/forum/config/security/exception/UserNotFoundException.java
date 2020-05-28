package br.com.home.forum.config.security.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends UsernameNotFoundException{
	
	private static final long serialVersionUID = 1L;
	
	private static String MESSAGE = "Dados inválidos!";
	
	public UserNotFoundException() {
		super(MESSAGE);
	}
	
	public UserNotFoundException(String msg) {
		super(msg);
	}

	public UserNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}


}
