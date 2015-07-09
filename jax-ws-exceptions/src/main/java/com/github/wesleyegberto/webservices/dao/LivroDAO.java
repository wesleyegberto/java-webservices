package com.github.wesleyegberto.webservices.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.wesleyegberto.webservices.model.Autor;
import com.github.wesleyegberto.webservices.model.Livro;

public class LivroDAO {

	public List<Livro> listaLivros() {
		List<Livro> livros = new ArrayList<>();
		
		livros.add(new Livro("SOA Aplicado", Arrays.asList(new Autor("Alexandre Saudete", null)), "Casa do Código", 2012, "Bom livro"));
		
		return livros;
	}

	public List<Livro> listaLivros(Integer pagina, Integer tamanhoPagina) {
		if(tamanhoPagina == null)
			tamanhoPagina = 20;
		
		if(pagina == null) 
			pagina = 0;
		else
			pagina = (pagina - 1) * tamanhoPagina;
		
		List<Livro> livros = listaLivros();
		
		if(pagina + tamanhoPagina > livros.size())
			return livros.subList(pagina, livros.size());
		return livros.subList(pagina, pagina + tamanhoPagina);
	}

	public void criaLivro(Livro livro) {
		System.out.println("Criando livro: " + livro);
	}

}
