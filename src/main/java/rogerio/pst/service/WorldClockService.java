package rogerio.pst.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
@ApplicationScoped
@RegisterRestClient
public interface WorldClockService {
 @GET
 @Path("/json/{timezone}/now")
 @Produces(MediaType.APPLICATION_JSON)
 WorldClock getNow(@PathParam("timezone") String timezone);
}
