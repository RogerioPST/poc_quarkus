package rogerio.pst.restclient;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;

@Path("")

@RegisterRestClient(configKey = "sso")

@RegisterProvider(SSOExceptionMapper.class)

public interface SSORestClient {

	@POST
	@Path("/auth/realms/intranet/protocol/openid-connect/token")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	TokenSSO solicitaToken(MultivaluedMap<String, String> input);
}
