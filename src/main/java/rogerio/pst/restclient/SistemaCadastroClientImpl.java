package rogerio.pst.restclient;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SistemaCadastroClientImpl implements SistemaCadastroClient {

	@Inject
	@RestClient
	SistemaCadastroRestClient sistemaCadastroRestClient;

	@Inject
	SSOClient ssoClient;

	@Override
	public SistemaCadastroDTO getInfoByDocumento(final String documento) throws Exception {

		String token = this.ssoClient.getToken();

		DadosClienteSistemaCadastroOutputDTO outputDTO = this.sistemaCadastroRestClient.getInfoByDocumento(documento,

				"dados,c", 1, "Bearer " + token, SystemProperties.CADASTRO_API_KEY);

		return converterSaida(outputDTO);

	}

	private SistemaCadastroDTO converterSaida(DadosClienteSistemaCadastroOutputDTO outputDTO) {

		SistemaCadastroDTO sistemaCadastroDTO = new SistemaCadastroDTO();

		sistemaCadastroDTO.setNome("");

		List<String> sistemaCadastroSegmentoDTOList = new ArrayList<>();

		outputDTO.getCarteira().forEach(l -> {

			String dto = "";

			sistemaCadastroSegmentoDTOList.add(dto);

		});

		sistemaCadastroDTO.setSegmentoDTO(sistemaCadastroSegmentoDTOList);

		return sistemaCadastroDTO;

	}

}
