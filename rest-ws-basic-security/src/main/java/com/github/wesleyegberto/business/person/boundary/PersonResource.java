package com.github.wesleyegberto.business.person.boundary;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.github.wesleyegberto.business.person.entity.Person;

@Path("/person")
public class PersonResource {
	@Inject
	private PersonManager personManager;
	
	@GET
	public List<Person> fetchAll() {
		return personManager.fetchAll();
	}
	
	@POST
	public Response create(Person person) {
		personManager.create(person);
		return Response.ok().build();
	}
}
