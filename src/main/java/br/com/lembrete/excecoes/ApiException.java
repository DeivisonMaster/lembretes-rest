package br.com.lembrete.excecoes;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ApiException extends WebApplicationException {
	private static final long serialVersionUID = 1L;

	public ApiException(int status, String message) {
		super(Response.status(status).entity(new Error(status, message)).build());
	}
	
	
}
