package com.github.wesleyegberto.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wesleyegberto.business.boundary.PersonManager;
import com.github.wesleyegberto.business.entity.Person;
import com.github.wesleyegberto.message.Message;
import com.github.wesleyegberto.util.jaxb.adapters.GenericList;

@WebServlet(name = "SimpleWSServlet", urlPatterns = "/services/servlet/simplews")
public class SimpleWSServlet extends HttpServlet {
	private static final long serialVersionUID = -1771793398238509174L;

	@Inject
	PersonManager personManager;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String rawId = req.getParameter("id");
		String jsonHeader = req.getHeader("Accept");

		// Verify the accepted response type
		boolean acceptJson = jsonHeader != null && jsonHeader.contains("json");

		if(rawId != null) {
			try {
				int id = Integer.parseInt(rawId);
				Person person = personManager.searchPersonById(id);

				writeResponse(resp, (person != null ? person : Message.build(1, "Person not found")), acceptJson);
			} catch(NumberFormatException ex) {
				writeResponse(resp, Message.build(2, "Invalid ID"), acceptJson);
			}
		} else {
			GenericList<Person> people = new GenericList<>(personManager.fetchAll());
			writeResponse(resp, people, acceptJson);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String rawBirthDate = req.getParameter("birthDate");

		int code = -1;
		String message = "";

		if(name == null || rawBirthDate == null) {
			code = 1;
			message = "Name and birth date are required";
		} else {
			Person person = new Person();
			person.setName(name);
			person.setBirthDate(LocalDate.parse(rawBirthDate));

			personManager.create(person);

			code = 0;
			message = "Person created successfully";
		}

		JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
		JsonGenerator generator = factory.createGenerator(resp.getWriter());

		generator.writeStartObject().write("code", code).write("message", message);
	}
	
	private void writeResponse(HttpServletResponse resp, Object objToWrite, boolean useJson) throws IOException {
		if(useJson) {
			writeJson(resp, objToWrite);
		} else {
			writeXml(resp, objToWrite);
		}
	}

	private void writeXml(HttpServletResponse resp, Object objToWrite) throws IOException {
		resp.setHeader("Content-type", "application/xml");

		JAXBContext jaxbCtx = null;
		Marshaller m = null;
		try {
			jaxbCtx = JAXBContext.newInstance(Message.class, GenericList.class, Person.class);
			m = jaxbCtx.createMarshaller();
			m.marshal(objToWrite, resp.getOutputStream());
		} catch(JAXBException e) {
			e.printStackTrace();
		}
	}

	private void writeJson(HttpServletResponse resp, Object objToWrite) throws IOException {
		resp.setHeader("Content-type", "application/json");
		resp.getWriter().print(new ObjectMapper().writeValueAsString(objToWrite));
	}

}
