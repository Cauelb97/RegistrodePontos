package com.user.registro.exceptions;

import java.text.MessageFormat;

public class GerenteNotFoundException extends RuntimeException{

	public GerenteNotFoundException(Long id) {
		super(MessageFormat.format("Could not find gerente with id: {0}", id));
	}
}
