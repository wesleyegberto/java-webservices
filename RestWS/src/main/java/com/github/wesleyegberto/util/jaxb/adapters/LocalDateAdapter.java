package com.github.wesleyegberto.util.jaxb.adapters;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * A JAX-B adapter to marshal/unmarshal LocalDate to/from String.
 * 
 * @author Wesley Egberto
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	public LocalDate unmarshal(String date) throws Exception {
		return LocalDate.parse(date);
	}

	public String marshal(LocalDate date) throws Exception {
		return date.toString();
	}

}