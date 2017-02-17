package br.com.padrao.controllers;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.padrao.daos.LivroDao;
import br.com.padrao.models.Livro;

@Controller
@Path("/integracao")
public class IntegracaoController {
	@Inject
	private LivroDao livroDao;
	@Inject
	private Result result;
	
	public void listaLivros(){
		List<Livro> livros = livroDao.listaTodos();
		result.use(Results.xml()).from(livros, "livros").serialize();
	}

}
