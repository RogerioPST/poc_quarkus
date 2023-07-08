package rogerio.pst.restclient;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.jbosslog.JBossLog;

@ApplicationScoped
@JBossLog
public class TwitchClientImpl implements TwitchClient {

	@Inject
	@RestClient
	TwitchRestClient twitchRestClient;

	@Inject
	TwitchTokenClient twitchTokenClient;

	@Override
	public TwitchDTO getInfo(final String id) throws Exception {

		String token = this.twitchTokenClient.getToken();
		
		log.info("################################### token: " +  token);

		TwitchDTO outputDTO = this.twitchRestClient.getInfo(id, "Bearer " + token, SystemProperties.TWITCH_CLIENT_ID);

		return outputDTO;

	}

}
