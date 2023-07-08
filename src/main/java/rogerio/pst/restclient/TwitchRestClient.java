package rogerio.pst.restclient;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("")
@RegisterRestClient(configKey = "twitch")
@RegisterProvider(GerenciadorApiExceptionMapper.class)
public interface TwitchRestClient {

	static final String SISTEMA = "Twitch";

	@GET
	@Path("/helix/videos")
	@Produces(MediaType.APPLICATION_JSON)
	//@LogTransacaoRestClient(operacao = ComunicacaoApiEnum.SistemaCadastro_CONSULTA_CLIENTE, sistema = SISTEMA)
	TwitchDTO getInfo(@QueryParam("id") String cpfcnpj,			
			@HeaderParam("Authorization") String token, @HeaderParam("Client-Id") String clientId);

}
