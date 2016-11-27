package it.com.github.wesleyegberto.uuidtokenauthentication;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

public class AuthenticatorResourceIT {

	private Client client;
	private WebTarget target;

	@Before
	public void init() {
		this.client = ClientBuilder.newClient();
		this.target = client.target("http://localhost:8080/uuid-token-authentication/resources/authenticate");
	}
	
	@Test
	public void should_not_validate() {
		JsonObject jsonLogin = Json.createObjectBuilder().add("username", "Joaozinho").add("password", "123123").build();
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(jsonLogin));
		assertThat(response.getStatus(), is(401));
	}
	
	@Test
	public void should_validate_and_got_token() {
		JsonObject jsonLogin = Json.createObjectBuilder().add("username", "Wesley").add("password", "123456").build();
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(jsonLogin));
		assertThat(response.getStatus(), is(200));
		JsonObject tokenEntity = response.readEntity(JsonObject.class);
		assertNotNull(tokenEntity);
		assertNotNull(tokenEntity.getString("auth_token"));
	}
}
