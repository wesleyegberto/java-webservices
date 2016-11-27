package com.github.wesleyegberto.uuidtokenauthentication.security.boundary;

import java.io.IOException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.github.wesleyegberto.uuidtokenauthentication.security.control.SecurityHeaderNames;
import com.github.wesleyegberto.uuidtokenauthentication.security.control.TokenManager;

@Provider
@Priority(value = javax.interceptor.Interceptor.Priority.APPLICATION)
@Authenticated
public class AuthenticationFilter implements ContainerRequestFilter {

	@Inject
	private TokenManager tokenManager;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if ("OPTION".equals(requestContext.getMethod())) {
			requestContext.abortWith(Response.ok().build());
			return;
		}
		
		String authToken = requestContext.getHeaderString(SecurityHeaderNames.TOKEN);
		
		if(!tokenManager.isTokenValid(authToken)) {
			requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
			return;
		}
	}
}
