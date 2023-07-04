package rogerio.pst.resource;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rogerio.pst.entity.Developer;

@Path("/developer")
public class DeveloperResource {
 private static final List<Developer> developers = new ArrayList<>();
 @POST
 @Consumes(MediaType.APPLICATION_JSON)
 public Response addDeveloper(Developer developer) {
 developers.add(developer);
 return Response.ok().build();
 }
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 public List<Developer> getDevelopers() {
 return developers;
 }
}

