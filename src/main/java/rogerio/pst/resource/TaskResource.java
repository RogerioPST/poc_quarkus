package rogerio.pst.resource;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import rogerio.pst.entity.Task;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {
	Set<Task> tasks = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

	public TaskResource() {
		tasks.add(new Task("First task", LocalDateTime.now().plusDays(3), false));
		tasks.add(new Task("Second task", LocalDateTime.now().plusDays(6), false));
	}

	@GET
	@Operation(summary = "Get all tasks", description = "Get the full list of tasks.")
	public Set<Task> list() {
		return tasks;
	}

	@POST
	@Operation(summary = "Create a new task")
	public Set<Task> add(
			@Parameter(required = true, content = @Content(schema = @Schema(implementation = Task.class))) Task task) {
		tasks.add(task);

		return tasks;
	}

	@DELETE
	@Operation(summary = "Remove the specified task")
	public Set<Task> delete(
			@Parameter(required = true, content = @Content(schema = @Schema(implementation = Task.class))) Task task) {
		tasks.removeIf(existingTask -> existingTask.equals(task));
		return tasks;
	}
}
