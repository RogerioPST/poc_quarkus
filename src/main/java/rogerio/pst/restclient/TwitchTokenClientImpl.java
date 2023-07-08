package rogerio.pst.restclient;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MultivaluedHashMap;

@ApplicationScoped

public class TwitchTokenClientImpl implements TwitchTokenClient {

	@Inject
	@RestClient
	TwitchTokenRestClient twitchTokenRestClient;

	private Map<String, TwitchToken> tokens = new HashMap<>();

	@Override
	public String getToken() throws Exception {

		var token = this.tokens.get(SystemProperties.TWITCH_CLIENT_ID);

		if (token != null && token.isValid()) {
			return token.getAccessToken();
		}

		final var params = new MultivaluedHashMap<String, String>();
		params.putSingle("client_id", SystemProperties.TWITCH_CLIENT_ID);
		params.putSingle("client_secret", SystemProperties.TWITCH_CLIENT_SECRET);
		params.putSingle("grant_type", "client_credentials");

		token = this.twitchTokenRestClient.solicitaToken(params);
		this.tokens.put(SystemProperties.TWITCH_CLIENT_ID, token);
		return token.getAccessToken();
	}

}
