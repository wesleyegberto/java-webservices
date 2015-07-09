package com.github.wesleyegberto.business.person.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.github.wesleyegberto.business.person.entity.Person;

@Stateless
public class PersonManager {

	@Inject
	private EntityManager em;
	
	public List<Person> fetchAll() {
		TypedQuery<Person> createQuery = em.createQuery("SELECT p FROM Person p", Person.class);
		return createQuery.getResultList();
	}

	public void create(Person person) {
		em.persist(person);
	}

}
