package com.github.wesleyegberto.business.person.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.github.wesleyegberto.business.person.entity.Person;

@Stateless
public class PersonManager {

	@PersistenceContext
	private EntityManager em;
	
	public List<Person> fetchAll() {
		TypedQuery<Person> createQuery = em.createNamedQuery(Person.FETCH_ALL, Person.class);
		return createQuery.getResultList();
	}

	public void create(Person person) {
		em.persist(person);
	}

}
