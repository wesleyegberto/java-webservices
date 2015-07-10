package com.github.wesleyegberto.business.person.boundary;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.wesleyegberto.business.person.entity.Person;

@Path("/person")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class PersonResource {
	@Inject
	private PersonManager personManager;
	
	@GET
	public List<Person> fetchAll() {
		return personManager.fetchAll();
	}
	
	@POST
	public Response create(@NotNull Person person) {
		personManager.create(person);
		return Response.ok().build();
	}
}
