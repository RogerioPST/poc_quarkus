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
@RegisterRestClient(configKey = "apimanager")
@RegisterProvider(GerenciadorApiExceptionMapper.class)
public interface SistemaCadastroRestClient {

	static final String SISTEMA = "SISTEMA_CADASTRO";

	@GET
	@Path("/cadastro/v1/clientes")
	@Produces(MediaType.APPLICATION_JSON)
	@LogTransacaoRestClient(operacao = ComunicacaoApiEnum.SistemaCadastro_CONSULTA_CLIENTE, sistema = SISTEMA)
	DadosClienteSistemaCadastroOutputDTO getInfoByDocumento(@QueryParam("cpfcnpj") String cpfcnpj,
			@QueryParam("campos") String campos,
			@QueryParam("classe") Integer classe,
			@HeaderParam("Authorization") String token, @HeaderParam("apiKey") String apiKey);

}
