package rogerio.pst.restclient;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public abstract class ResponseConfigResource {

	@Context

	protected HttpHeaders httpHeaders;

	public Response build(Status status, Object object) {

		return Response.status(status).entity(object)

				.header("Access-Control-Max-Age", "1209600")

				.header("Cache-Control", "no-cache, no-store, must-revalidate").header("Pragma", "no-cache")

				.header("Expires", 0).header("X-Frame-Options", "DENY").header("X-XSS-Protection", "1").build();

	}

}
