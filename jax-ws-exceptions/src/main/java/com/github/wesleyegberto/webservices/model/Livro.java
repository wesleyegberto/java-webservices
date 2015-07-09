package com.github.wesleyegberto.webservices.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.wesleyegberto.webservices.util.AutorAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Livro {

	private String nome;
	@XmlElementWrapper(name = "autores")
	@XmlElement(name = "autor")
	@XmlJavaTypeAdapter(value = AutorAdapter.class)
	private List<Autor> autores;
	private String editora;
	private Integer anoPublicacao;
	private String resumo;

	public Livro() {
	}

	public Livro(String nome, List<Autor> autores, String editora,
			Integer anoPublicacao, String resumo) {
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

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
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

	@Override
	public String toString() {
		return "Livro [nome=" + nome + ", autores=" + autores + ", editora="
				+ editora + ", anoPublicacao=" + anoPublicacao + "]";
	}

}
