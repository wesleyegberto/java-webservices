package com.github.wesleyegberto.business.boundary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.wesleyegberto.business.entity.Person;

@Singleton
public class PersonManager {
	@PersistenceContext
	private EntityManager em;
	private List<Person> people = new ArrayList<>();

	private int fakeId = 0;
	
	public PersonManager() {
		people.add(new Person(++fakeId, "Bruce Banner", LocalDate.now()));
		people.add(new Person(++fakeId, "Tony Stark", LocalDate.now()));
		people.add(new Person(++fakeId, "Bruce Wayne", LocalDate.now()));
		people.add(new Person(++fakeId, "Clark Kent", LocalDate.now()));
		people.add(new Person(++fakeId, "Peter Park", LocalDate.now()));
	}
	
	public PersonManager(EntityManager em) {
		this.em = em;
	}
	
	public Person searchPersonById(int id) {
		Optional<Person> person = people.stream().filter(p -> p.getId() == id).findFirst();
		
		if(person.isPresent())
			return person.get();
		return null;
	}
		
	public List<Person> fetchAll() {
		return people;
	}
	
	public void create(Person person) {
		person.setId(++fakeId);
		people.add(person);
	}
}
