package com.github.wesleyegberto.endpoints;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.wesleyegberto.business.boundary.PersonManager;
import com.github.wesleyegberto.business.entity.Person;
import com.github.wesleyegberto.rest.entity.Message;

@Path("/person")
public class PersonResource {
	private static int SUCCESSFUL_OPER = 0;
	private static int INVALID_DATA = 1;
	private static int PERSON_NOT_FOUND = 10;
	
	
	@Inject
	private PersonManager personManager;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Person> getPeople() {
		return personManager.fetchAll();
	}
	
	@GET
	@Path("/{id: \\d+}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getPersonById(@PathParam("id") int id) {
		Person person = personManager.searchPersonById(id);
		if(person == null)
			return Response.status(Status.NOT_FOUND).entity(Message.build(PERSON_NOT_FOUND, "Person not found")).build();
		
		return Response.ok(person).build();
	}
	
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createPerson(@FormParam("name") String name, @FormParam("birthDate") String birthDate) {
		if(name == null || birthDate == null)
			return Response.status(Status.BAD_REQUEST)
					.entity(Message.build(INVALID_DATA, "Name and birth date are required"))
					.build();
		
		try {
			Person person = new Person(name, LocalDate.parse(birthDate));
			personManager.create(person);
			return Response.ok(Message.build(SUCCESSFUL_OPER, "Person created successfully")).build();
		} catch(DateTimeParseException ex) {
			return Response.status(Status.BAD_REQUEST).entity(Message.build(INVALID_DATA, "Invalid birth date: " + ex.getMessage())).build();
		}
	}
}
