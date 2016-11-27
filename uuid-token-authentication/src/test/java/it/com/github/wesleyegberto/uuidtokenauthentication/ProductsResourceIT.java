package it.com.github.wesleyegberto.uuidtokenauthentication;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.UUID;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

public class ProductsResourceIT {

	private Client client;
	private WebTarget target;
	private String authToken;

	@Before
	public void init() {
		this.client = ClientBuilder.newClient();
		this.target = client.target("http://localhost:8080/uuid-token-authentication/resources/products");

		JsonObject jsonLogin = Json.createObjectBuilder().add("username", "Wesley").add("password", "123456").build();
		Response response = client.target("http://localhost:8080/uuid-token-authentication/resources/authenticate")
									.request(MediaType.APPLICATION_JSON).post(Entity.json(jsonLogin));
		assertThat(response.getStatus(), is(200));
		JsonObject tokenEntity = response.readEntity(JsonObject.class);
		authToken = tokenEntity.getString("auth_token");
	}
	
	@Test
	public void should_got_unauthorized() {
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		assertThat(response.getStatus(), is(401));

		JsonObject firstProd = Json.createObjectBuilder().add("name", "Does not matter").build();
		response = target.request().post(Entity.json(firstProd));
		assertThat(response.getStatus(), is(401));
	}

	@Test
	public void should_got_unauthorized_with_bad_token() {
		Response response = target.request(MediaType.APPLICATION_JSON)
									.header("AUTH_TOKEN", UUID.randomUUID().toString())
									.get();
		assertThat(response.getStatus(), is(401));
	}
	
	@Test
	public void should_persist_and_list() {
		// Get the products and to verify if it is empty
		Response response = target.request(MediaType.APPLICATION_JSON)
								.header("AUTH_TOKEN", authToken)
								.get();
		JsonArray products = response.readEntity(JsonArray.class);
		assertThat(response.getStatus(), is(200));
		assertNotNull(products);
		final int sizeBeforePost = products.size();
		
		// Create the first product
		final String FIRST_PRODUCT_NAME = "Product " + sizeBeforePost;
		JsonObject firstProd = Json.createObjectBuilder().add("name", FIRST_PRODUCT_NAME).build();
		response = target.request()
						.header("AUTH_TOKEN", authToken)
						.post(Entity.json(firstProd));
		assertThat(response.getStatus(), is(204));
		
		// Get the products to verify if it contains the first product
		response = target.request(MediaType.APPLICATION_JSON)
						.header("AUTH_TOKEN", authToken)
						.get();
		products = response.readEntity(JsonArray.class);
		assertThat(response.getStatus(), is(200));
		assertNotNull(products);
		assertThat(products.size(), is(sizeBeforePost + 1));
		products.forEach(System.out::println);
		assertTrue(products.stream().anyMatch(rawJson -> {
			JsonObject jsonObject = Json.createReader(new StringReader(rawJson.toString())).readObject();
			return FIRST_PRODUCT_NAME.equals(jsonObject.getString("name"));
		}));
	}
}
