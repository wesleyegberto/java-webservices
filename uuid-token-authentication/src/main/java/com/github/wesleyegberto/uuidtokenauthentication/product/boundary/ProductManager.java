package com.github.wesleyegberto.uuidtokenauthentication.product.boundary;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ejb.Stateless;

import com.github.wesleyegberto.uuidtokenauthentication.product.entity.Product;

@Stateless
public class ProductManager {

	private static List<Product> database = new CopyOnWriteArrayList<>();
	private static AtomicInteger lastId = new AtomicInteger(0);
	
	public List<Product> fetchAll() {	
		return Collections.unmodifiableList(database);
	}

	public void save(Product product) {
		product.setId(lastId.incrementAndGet());
		database.add(product);
	}

	public boolean delete(int id) {
		return database.removeIf(p -> p.getId() == id);
	}
}
