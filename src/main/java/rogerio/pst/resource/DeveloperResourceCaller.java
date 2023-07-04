package rogerio.pst.resource;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import rogerio.pst.entity.Developer;
import rogerio.pst.service.DeveloperService;

@Path("/dev")
public class DeveloperResourceCaller {

	@RestClient
	DeveloperService developerService;

	@GET	
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Developer> getDevs() {
		return developerService.getDevelopers();
	}
}