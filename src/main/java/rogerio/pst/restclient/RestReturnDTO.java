package rogerio.pst.restclient;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter

@Setter

@XmlAccessorType(XmlAccessType.FIELD)

@JsonInclude(JsonInclude.Include.NON_NULL)

@ToString

public class RestReturnDTO<T> extends ResponseConfigResource implements RestReturnBuilder<T> {

	private static final long serialVersionUID = -3805722825706428704L;

	/**
	 * 
	 * Atributo data serve apenas para substituir este mesmo atributo na classe
	 * deprequiada
	 * 
	 * {@link ResponseBuilder.java}.
	 * 
	 */

	private List<T> data;

	private T retorno;

	private List<T> retornoList;

	private Status httpStatus;

	private Boolean temErro;

	private Boolean temWarn;

	private List<String> msgsErro;

	private List<String> msgsWarn;

	private Integer totalDeLinhas;

	private PaginacaoDTO paginacao;

	@Override

	public RestReturnBuilder<T> success() {

		this.httpStatus = Status.OK;

		this.temErro = false;

		this.temWarn = false;

		return this;

	}

	@Override

	public RestReturnBuilder<T> success(T retorno) {

		this.retorno = retorno;

		return success();

	}

	@Override

	public RestReturnBuilder<T> success(List<T> retornoList) {

		this.retornoList = retornoList;

		return success();

	}

	/**
	 * 
	 * Metodo utilizado para retornar mensagem de aviso ao usuário, http 200,
	 * 
	 * mostrando na propria pagina utilizada pelo usuario
	 * 
	 */

	@Override

	public RestReturnBuilder<T> warn(String message) {

		this.httpStatus = Status.OK;

		this.temWarn = true;

		this.temErro = false;

		return this.appendMessageWarn(message);

	}

	/**
	 * 
	 * Metodo utilizado para retornar erro, http 500, com mensagem específica para
	 * 
	 * pagina de erro
	 * 
	 */

	@Override

	public RestReturnBuilder<T> error(String message) {

		this.httpStatus = Status.INTERNAL_SERVER_ERROR;

		this.temWarn = false;

		this.temErro = true;

		return this.appendMessageError(message);

	}

	@Override

	public RestReturnBuilder<T> appendRetorno(T retorno) {

		this.retorno = retorno;

		return this;

	}

	@Override

	public RestReturnBuilder<T> appendRetornoList(List<T> retornoList) {

		this.retornoList = retornoList;

		return this;

	}

	@Override

	public RestReturnBuilder<T> appendMessageError(String message) {

		if (this.msgsErro == null) {

			this.msgsErro = new ArrayList<>();

		}

		this.msgsErro.add(message);

		this.temErro = true;

		return this;

	}

	@Override

	public RestReturnBuilder<T> appendMessageWarn(String message) {

		if (this.msgsWarn == null) {

			this.msgsWarn = new ArrayList<>();

		}

		this.msgsWarn.add(message);

		this.temWarn = true;

		return this;

	}

	@Override

	public RestReturnBuilder<T> appendTotalLinhas(Integer totalLinhas) {

		this.totalDeLinhas = totalLinhas;

		return this;

	}

	@Override

	public RestReturnBuilder<T> appendHttpStatus(Status httpStatus) {

		this.httpStatus = httpStatus;

		return this;

	}

	@Override

	public Response build() {

		return build(this.httpStatus, this);

	}

	@Deprecated

	public void montarRetorno(T retorno, boolean temErro) {

		this.temErro = temErro;

		this.retorno = retorno;

	}

	@Deprecated

	public void montarRetorno(T retorno) {

		this.retorno = retorno;

	}

	@Deprecated

	public void montarRetorno(List<T> retornoList) {

		this.temErro = false;

		this.retornoList = retornoList;

	}

	@Deprecated

	public void montarRetorno(T retorno, boolean temErro, boolean temWarn) {

		this.temErro = temErro;

		this.retorno = retorno;

		this.temWarn = temWarn;

	}

	@Deprecated

	public void montarRetorno(boolean temErro, boolean temWarn) {

		this.temErro = temErro;

		this.temWarn = temWarn;

	}

	@Deprecated

	public void montarRetorno(T retorno, boolean temErro, boolean temWarn, String message) {

		this.temErro = temErro;

		this.retorno = retorno;

		this.temWarn = temWarn;

		if (temErro) {

			addMessage(message);

		} else {

			addWarnMessage(message);

		}

	}

	@Deprecated

	public void montarRetorno(boolean temErro, boolean temWarn, String message) {

		this.temErro = temErro;

		this.retorno = null;

		this.temWarn = temWarn;

		if (temErro) {

			addMessage(message);

		} else {

			addWarnMessage(message);

		}

	}

	@Deprecated

	public void montarRetorno(List<T> retornoList, boolean temErro, boolean temWarn, String message) {

		this.temErro = temErro;

		this.retornoList = retornoList;

		this.temWarn = temWarn;

		if (temErro) {

			addMessage(message);

		} else {

			addWarnMessage(message);

		}

	}

	@Deprecated

	public void addMessage(String message) {

		if (getMsgsErro() == null)

			msgsErro = new ArrayList<>();

		getMsgsErro().add(message);

	}

	@Deprecated

	public void addWarnMessage(String message) {

		if (message == null)

			return;

		if (getMsgsWarn() == null)

			msgsWarn = new ArrayList<>();

		getMsgsWarn().add(message);

	}

}
