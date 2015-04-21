package com.github.wesleyegberto.util.jaxb.adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class GenericList<T> {
	@XmlElementWrapper(name = "item")
	private List<T> elements;

	public GenericList() {
		elements = new ArrayList<T>();
	}

	public GenericList(List<T> elements) {
		super();
		this.elements = elements;
	}

	public void setElements(List<T> elements) {
		this.elements = elements;
	}

	public List<T> getElements() {
		return elements;
	}

}
