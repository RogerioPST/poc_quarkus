package rogerio.pst.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.qute.Qute;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.vertx.http.runtime.filters.Filters;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rogerio.pst.entity.Developer;
import rogerio.pst.interceptor.CalculaTempoExecucao;

@Path("/")
public class GreetingResource {

	// Accessing Configuration Properties NOT Programmatically
	@ConfigProperty(name = "greeting.message", defaultValue = "true")
	String message;

	@ConfigProperty(name = "greeting.suffix")
	List<String> suffixes;

	// Accessing Configuration Properties Programmatically
	@Inject
	Config config;

	@Inject
	Validator validator;

	private static final List<Developer> developers = new ArrayList<>();
	
	@Inject
	Template hello;
	//https://quarkus.io/guides/qute-reference
	
	
	
	@GET
	@Path("/template")
	@Produces(MediaType.TEXT_PLAIN)
	public TemplateInstance helloTemplate() {
	 final String name = "Alex";
	 
	 String teste = Qute.fmt("Hello {}!", "Lucy"); 
	// => Hello Lucy!

	Qute.fmt("Hello {name} {surname ?: 'Default'}!", Map.of("name", "Andy")); 
	// => Hello Andy Default!

	Qute.fmt("<html>{header}</html>").contentType("text/html").data("header", "<h1>My header</h1>").render(); 
	// <html>&lt;h1&gt;Header&lt;/h1&gt;</html> 

	Qute.fmt("I am {#if ok}happy{#else}sad{/if}!", Map.of("ok", true)); 
	// => I am happy!
	 return hello.data("name", name);	
	}

	@GET
	@Path("/template/str")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloTemplateString() {	 
	 
	 String teste = Qute.fmt("Hello {}!", "Lucy"); 
	// => Hello Lucy!

	teste =Qute.fmt("Hello {name} {surname ?: 'Default'}!", Map.of("name", "Andy")); 
	// => Hello Andy Default!

	teste = Qute.fmt("<html>{header}</html>").contentType("text/html").data("header", "<h1>My header</h1>").render(); 
	// <html>&lt;h1&gt;Header&lt;/h1&gt;</html> 

	teste = Qute.fmt("I am {#if ok}happy{#else}sad{/if}!", Map.of("ok", true)); 
	// => I am happy!
	return teste;
	}
	

	// Validating Objects Programmatically ; instead of using a declarative way with
	// @Valid annotations.
	@POST
	@Path("/programmaticvalidation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProgrammaticValidation(Developer developer) {
		Set<ConstraintViolation<Developer>> violations = validator.validate(developer);
		if (violations.isEmpty()) {
			developers.add(developer);
			return Response.ok().build();
		} else {
			JsonArrayBuilder errors = Json.createArrayBuilder();
			for (ConstraintViolation<Developer> violation : violations) {
				errors
					.add(Json.createObjectBuilder().add("path", violation.getPropertyPath().toString())
					.add("message", violation.getMessage()));
			}
			return Response.status(Response.Status.BAD_REQUEST).entity(errors.build()).build();
		}
	}

	@GET
	@Path("/list")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloList() {

		config.getPropertyNames().forEach(p -> System.out.println(p));
		config.getValue("greeting.message", String.class);
		// You can access the Config class without using CDI by calling
		// ConfigProvider.getConfig() method
		ConfigProvider.getConfig().getValue("greeting.message", String.class);

		return message + suffixes.get(1);
	}

	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	@CalculaTempoExecucao
	public String hello() {
		return "Hello from RESTEasy Reactive";
	}

	@GET
	public Response buscar(@Observes Filters filters) {
		filters.register(rc -> {
			rc.response().putHeader("V-Header", "Header added by VertX Filter");
			rc.next();
		}, 10);

		return null;

	}
}