package com.github.wesleyegberto.uuidtokenauthentication.cors;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsEnablerFilter implements ContainerResponseFilter {

	private final static Logger log = Logger.getLogger(CorsEnablerFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		log.info("Executing REST response filter");

		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		responseContext.getHeaders().add( "Access-Control-Allow-Headers", "AUTH_TOKEN");
	}

}
