package rogerio.pst.exception;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;

public abstract class ProjetoBaseHandler {
	
	@Context
	protected HttpHeaders httpHeaders;

}
