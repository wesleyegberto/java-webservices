package com.github.wesleyegberto.webservices.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.github.wesleyegberto.webservices.dao.LivroDAO;
import com.github.wesleyegberto.webservices.model.Livro;
import com.github.wesleyegberto.webservices.model.Usuario;

@WebService
public class ListagemLivros {

	public List<Livro> listaLivros() {
		LivroDAO livroDao = new LivroDAO();
		return livroDao.listaLivros();
	}

	@RequestWrapper(
		className = "com.github.wesleyegberto.soaaplicado.cap3.service.jaxws.ListaLivrosPaginacao",
		localName = "listaLivrosPaginacao"
	)
	@ResponseWrapper(
		className = "com.github.wesleyegberto.soaaplicado.cap3.service.jaxws.ListaLivrosPaginacaoResponse",
		localName = "livrosPaginados"
	)
	@WebResult(name = "livro")
	@WebMethod(operationName = "listaLivrosPaginado")
	public List<Livro> listaLivros(Integer pagina, Integer tamanhoPagina) {
		LivroDAO livroDao = new LivroDAO();
		return livroDao.listaLivros(pagina, tamanhoPagina);
	}

	public String criaLivro(@WebParam(name = "livro") Livro livro,
			@WebParam(name = "usuario", header = true) Usuario usuario)
			throws UsuarioNaoAutorizadoException {
		if (!"wesley".equals(usuario.getUsuario()) || !"123".equals(usuario.getSenha())) {
			throw new UsuarioNaoAutorizadoException("Usuário não autorizado");
		}
		LivroDAO livroDao = new LivroDAO();
		livroDao.criaLivro(livro);
		
		return "Livro criado com sucesso";
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/livros3", new ListagemLivros());
		System.out.println("Serviço inicializado...");
	}

}
