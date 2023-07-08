package rogerio.pst.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import rogerio.pst.restclient.TwitchClient;
import rogerio.pst.restclient.TwitchDTO;

@Path("/twitch")
public class TwitchResource {

	@Inject
	TwitchClient twitchClient;

	@GET	
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public TwitchDTO getDevs() throws Exception {
		return twitchClient.getInfo("987654321");
	}
}