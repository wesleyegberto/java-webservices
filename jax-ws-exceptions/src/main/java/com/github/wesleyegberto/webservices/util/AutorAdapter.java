package com.github.wesleyegberto.webservices.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.wesleyegberto.webservices.model.Autor;

public class AutorAdapter extends XmlAdapter<String, Autor> {

	@Override
	public String marshal(Autor autor) throws Exception {
		return autor.getNome();
	}

	@Override
	public Autor unmarshal(String nome) throws Exception {
		return new Autor(nome, null);
	}

}
