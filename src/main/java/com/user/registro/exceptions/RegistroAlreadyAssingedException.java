package com.user.registro.exceptions;

import java.text.MessageFormat;

public class RegistroAlreadyAssingedException extends RuntimeException{
	public RegistroAlreadyAssingedException(final Long registroId, final Long userId ) {
		super(MessageFormat.format("Registro: {0} is already assigned to user: {1}", registroId, userId));
	}
}
