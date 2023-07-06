package rogerio.pst.exception;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import rogerio.pst.dto.RestReturnDTO;

@Provider
public class GenericExceptionHandler extends ProjetoBaseHandler implements ExceptionMapper<Exception>{
	
	
	@Override		
	public Response toResponse(final Exception exception) {
		exception.printStackTrace();
		
		List<String> listMessageError = new ArrayList<>();
		int status;
		
		listMessageError.add("Ocorreu um erro inesperado");
		status = Status.INTERNAL_SERVER_ERROR.getStatusCode();
		
		RestReturnDTO<?> restReturn = new RestReturnDTO<>();
		restReturn.setTemErro(Boolean.TRUE);
		restReturn.setMsgsErro(listMessageError);
		
		return Response.status(status).entity(restReturn).type("application/json").build();
	}

}
