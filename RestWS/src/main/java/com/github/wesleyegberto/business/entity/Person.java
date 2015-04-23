package com.github.wesleyegberto.business.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.wesleyegberto.util.jaxb.adapters.LocalDateAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate birthDate;

	public Person() {
	}

	public Person(String name, LocalDate birthDate) {
		this(0, name, birthDate);
	}

	public Person(long id, String name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
