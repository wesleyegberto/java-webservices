package com.github.wesleyegberto.uuidtokenauthentication.security.boundary;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.wesleyegberto.uuidtokenauthentication.security.control.AuthenticationException;
import com.github.wesleyegberto.uuidtokenauthentication.security.control.UserAuthenticator;
import com.github.wesleyegberto.uuidtokenauthentication.security.entity.User;

@Path("/authenticate")
public class AuthenticatorResource {

	@Inject
	private UserAuthenticator authenticator;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response authenticate(@NotNull User user) {
		try {
			String token = authenticator.validateUser(user);
			JsonObject jsonToken = Json.createObjectBuilder().add("auth_token", token).build();
			return getNoCacheResponseBuilder(Status.OK).entity(jsonToken).build();
		} catch (AuthenticationException ex) {
			return getNoCacheResponseBuilder(Status.UNAUTHORIZED).build();
		}
	}

	private Response.ResponseBuilder getNoCacheResponseBuilder(Response.Status status) {
		CacheControl cc = new CacheControl();
		cc.setNoCache(true);
		cc.setMaxAge(-1);
		cc.setMustRevalidate(true);
		return Response.status(status).cacheControl(cc);
	}

}
