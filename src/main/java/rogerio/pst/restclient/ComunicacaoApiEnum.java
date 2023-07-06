package rogerio.pst.restclient;

import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

public enum ComunicacaoApiEnum {
	SERVICO_EXTRATO_MENSAL("/extrato/mensal", "Serviço Extrato Mensal", "COD_FUNCIONALIDADE"),
	NOT_FOUND("NOT_FOUND", "NOT_FOUND", null), 
	SistemaCadastro_CONSULTA_CLIENTE("/cadastro", "serv de cadastro", null);

	private ComunicacaoApiEnum(String url, String servico, String funcionalidade) {

		this.url = url;

		this.servico = servico;

		this.funcionalidade = funcionalidade;

	}

	private String url;

	private String servico;

	private String funcionalidade;

	public String getUrl() {

		return url;

	}

	public String getServico() {

		return servico;

	}

	public String getFuncionalidade() {

		return funcionalidade;

	}

	public static ComunicacaoApiEnum getByUrl(String url) {

		Optional<ComunicacaoApiEnum> result = Arrays.asList(ComunicacaoApiEnum.values()).stream()

				.filter(f -> f.url.equalsIgnoreCase(url) || url.equalsIgnoreCase(StringUtils.chop(f.url))).findFirst();

		return result.isPresent() ? result.get() : ComunicacaoApiEnum.NOT_FOUND;

	}

	public static ComunicacaoApiEnum getByServico(String servico) {

		Optional<ComunicacaoApiEnum> result = Arrays.asList(ComunicacaoApiEnum.values()).stream()

				.filter(f -> f.servico.equalsIgnoreCase(servico)).findFirst();

		return result.isPresent() ? result.get() : ComunicacaoApiEnum.NOT_FOUND;

	}

}
