package rogerio.pst.service;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HEAD;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import rogerio.pst.entity.Developer;

@Path("/developer")
@RegisterRestClient
@Consumes("application/json")
@Produces("application/json")
public interface DeveloperService {
	@HEAD
	Response head();

	@GET
	List<Developer> getDevelopers();

	@POST
	Response createDeveloper(@HeaderParam("Authorization") String authorization, Developer developer);

	//@DELETE
	//@Path("/{userId}")
	//Response deleteUser(@CookieParam("AuthToken") String authorization, @PathParam("developerId") Long developerId);
}
