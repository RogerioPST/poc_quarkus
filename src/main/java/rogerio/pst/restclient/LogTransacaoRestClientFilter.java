package rogerio.pst.restclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.jbosslog.JBossLog;

@JBossLog

@Provider

public class LogTransacaoRestClientFilter implements ClientRequestFilter, ClientResponseFilter {

	static final String LOG_TRANSACAO_REQUEST_PROPERTY = "logTransacaoRestClientRequestProperty";

	static final ObjectMapper mapper = new ObjectMapper();

	static final String SISTEMA = "SISTEMA";

	@Context

	ResourceInfo resourceInfo;

	@Inject

	LogTransacaoService logTransacaoService;

	@Override

	public void filter(ClientRequestContext requestContext) throws IOException {

		log.debug(
				"*** [LogTransacaoRestClient] Log de transacao habilitado: " + SystemProperties.LOG_TRANSACAO_ENABLED);

		// Se o log de transacao estiver desabilitado, nao faz nada

		if (!SystemProperties.LOG_TRANSACAO_ENABLED) {

			return;

		}

		// Identificando a anotacao LogTransacao para identificar o codigo da transacao
		// e o nome do servico a serem gravados no banco

		final Method method = (Method) requestContext

				.getProperty("org.eclipse.microprofile.rest.client.invokedMethod");

		LogTransacaoRestClient logTransacaoRestClient = getLogTransacaoRestClient(method);

		// se o servico NAO estiver anotado com @LogTransacaoRestClient, nao precisa
		// gravar os dados da operacao no banco

		if (logTransacaoRestClient == null) {

			return;

		}

		ComunicacaoApiEnum comunicacaoApiEnum = logTransacaoRestClient.operacao();

		if (comunicacaoApiEnum == null) {

			return;

		}

		String requestUrl = requestContext.getUri().getScheme() + requestContext.getUri().getRawSchemeSpecificPart();

		String bodyRequest = "{}";

		log.debug(">>> [LogTransacaoRestClient] :: Gravando dados de chamada para sistema externo: " + requestUrl);

		// buscando body de entrada do servico(valido apenas para json ou texto)

		if (requestContext.getMediaType() != null

				&& (requestContext.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE)

						|| requestContext.getMediaType().isCompatible(MediaType.TEXT_PLAIN_TYPE))) {

			// extraindo body do request

			Object entity = requestContext.getEntity();

			if (entity != null) {

				// se o body for json, converte em String

				if (requestContext.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE)) {

					bodyRequest = JsonUtil.toJson(entity);

				} else {

					bodyRequest = entity.toString();

				}

			}

		}

		try {

			ComunicacaoApiEntity logTransacaoRequest = new ComunicacaoApiEntity(comunicacaoApiEnum.getServico(),

					logTransacaoRestClient.sistema(), true, requestUrl, bodyRequest);

			// gravando dados da transcao no banco de dados

			logTransacaoRequest = this.logTransacaoService.gravaLogTransacao(logTransacaoRequest);

			// adicionando dados do log no request para ser recuperado e vinculado ao log do
			// response

			requestContext.setProperty(LOG_TRANSACAO_REQUEST_PROPERTY, logTransacaoRequest);

		} catch (Throwable e) {

			log.error("*** [LogTransacaoRestClient] :: Erro ao gravar log de transacao do request");

			e.printStackTrace();

		}

	}

	@Override

	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {

		log.debug(
				"*** [LogTransacaoRestClient] Log de transacao habilitado: " + SystemProperties.LOG_TRANSACAO_ENABLED);

		// Se o log de transacao estiver desabilitado, nao faz nada

		if (!SystemProperties.LOG_TRANSACAO_ENABLED) {

			return;

		}

		// buscando no request os dados da operacao gravada em banco para vincular ao
		// registro do response

		ComunicacaoApiEntity comunicacaoRequest = (ComunicacaoApiEntity) requestContext

				.getProperty(LOG_TRANSACAO_REQUEST_PROPERTY);

		// se nao encontrar os dados do request, nao faz nada

		if (comunicacaoRequest == null) {

			return;

		}

		// buscando body de saida do servico (valido apenas para json ou texto)

		String bodyResponse = "{}";

		if (responseContext.getMediaType() != null

				&& (responseContext.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE))

				|| responseContext.getMediaType().isCompatible(MediaType.TEXT_PLAIN_TYPE)) {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			responseContext.getEntityStream().transferTo(baos);

			InputStream entityStreamReposicao = new ByteArrayInputStream(baos.toByteArray());

			bodyResponse = baos.toString();

			responseContext.setEntityStream(entityStreamReposicao);// repondo o response para nao dar
																	// NullPointerException

		}

		try {

			ComunicacaoApiEntity comunicacaoResponse = new ComunicacaoApiEntity(comunicacaoRequest.getServico(),

					comunicacaoRequest.getSistemaOrigem(), false, comunicacaoRequest.getUrlEntrada(), bodyResponse);

			comunicacaoResponse.setComunicacaoApi(comunicacaoRequest);

			// gravando dados do response no banco de dados

			this.logTransacaoService.gravaLogTransacao(comunicacaoResponse);

		} catch (Exception e) {

			log.error("*** [LogTransacaoRestClient] :: Erro ao gravar log de transacao do response");

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * Recuperando dados da anotacao LogTransacaoRestClient
	 * 
	 * para que possamos ter os dados do tipo e operacao e do sistema de destino
	 *
	 * 
	 * 
	 * @param m
	 * 
	 * @return
	 * 
	 */

	LogTransacaoRestClient getLogTransacaoRestClient(Method m) {

		LogTransacaoRestClient[] logs = m.getAnnotationsByType(LogTransacaoRestClient.class);

		if (logs != null && logs.length > 0) {

			return logs[0];

		}

		return null;

	}

}
