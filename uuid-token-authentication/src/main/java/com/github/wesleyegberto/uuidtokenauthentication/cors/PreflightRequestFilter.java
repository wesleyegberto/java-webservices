package com.github.wesleyegberto.uuidtokenauthentication.cors;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class PreflightRequestFilter implements ContainerRequestFilter {

	private final static Logger log = Logger.getLogger(PreflightRequestFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		log.info("Executing REST request filter");
		if ("OPTION".equals(requestContext.getMethod())) {
			log.info("HTTP Method (OPTIONS) - Detected!");
			requestContext.abortWith(Response.ok().build());
		}
	}

}
