package rogerio.pst.resource;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import rogerio.pst.service.WorldClock;
import rogerio.pst.service.WorldClockService;



@Path("/now")
public class WorldClockResource {
 @ConfigProperty(name = "clock.host", defaultValue = "http://worldclockapi.com")
	String clockHost;
 
 //private Client client = ClientBuilder.newClient();
 
 @RestClient
 WorldClockService worldClockService;
 @GET
 @Path("{timezone}/mp")
 @Produces(MediaType.APPLICATION_JSON)
 public WorldClock getCurrentTimeMp(@PathParam("timezone") String timezone) {
  return worldClockService.getNow(timezone);
 }
}
