package com.github.wesleyegberto.webservices.model;

import java.util.List;

public class Livro {

	private String nome;
	private List<String> autores;
	private String editora;
	private Integer anoPublicacao;
	private String resumo;

	public Livro() {
	}

	public Livro(String nome, List<String> autores, String editora,
			Integer anoPublicacao, String resumo) {
		super();
		this.nome = nome;
		this.autores = autores;
		this.editora = editora;
		this.anoPublicacao = anoPublicacao;
		this.resumo = resumo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> getAutores() {
		return autores;
	}

	public void setAutores(List<String> autores) {
		this.autores = autores;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

}
