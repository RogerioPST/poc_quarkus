package rogerio.pst.restclient;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import jakarta.ws.rs.core.Response;
import lombok.extern.jbosslog.JBossLog;
import rogerio.pst.exception.ProjetoException;

@JBossLog

public class GerenciadorApiExceptionMapper implements ResponseExceptionMapper<Exception> {

	static final String SERVICO_EXTERNO_FORA = "Serviço externo temporariamente indisponível.";

	@Override

	public Exception toThrowable(Response response) {

		String entity = response.readEntity(String.class);

		if (response.getStatus() == 404) {

			return new ProjetoException(SERVICO_EXTERNO_FORA, true, true);

		}

		log.error("*** Erro ao chamar o API-MANAGER :: STATUS: " + response.getStatus() + " :: " + entity);

		return new Exception();// sera interceptado pelo GenericExceptionHandler e retornara um erro 500 Padrao

	}

}
