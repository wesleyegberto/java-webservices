package com.github.wesleyegberto.uuidtokenauthentication.product.boundary;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.wesleyegberto.uuidtokenauthentication.product.entity.Product;
import com.github.wesleyegberto.uuidtokenauthentication.security.boundary.Authenticated;

@Path("/products")
@Authenticated
public class ProductsResource {

	@Inject
	private ProductManager productsManager;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Product> fetchAll() {
		return productsManager.fetchAll();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public void save(@NotNull Product product) {
		productsManager.save(product);
	}

	@Path("/{id: \\d+}")
	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public void delete(@Min(value = 1, message = "Invalid id") @PathParam("id") int id) {
		productsManager.delete(id);
	}
}
