package com.user.registro.exceptions;

import java.text.MessageFormat;

public class UserIsAlreadyAssingnedException extends RuntimeException{
	public UserIsAlreadyAssingnedException(final Long userId, final Long gerenteId ) {
		super(MessageFormat.format("User: {0} is already assigned to gerente: {1}", userId, gerenteId));
	}

}
