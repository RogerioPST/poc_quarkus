package rogerio.pst.restclient;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import jakarta.ws.rs.core.Response;
import lombok.extern.jbosslog.JBossLog;

@JBossLog

public class SSOExceptionMapper implements ResponseExceptionMapper<Exception> {

	@Override

	public Exception toThrowable(Response response) {

		String entity = response.readEntity(String.class);

		log.error("*** Erro ao chamar o SSO :: STATUS: " + response.getStatus() + " :: " + entity);

		return new Exception();// sera interceptado pelo GenericExceptionHandler e retornara um erro 500 padrao

	}

}
