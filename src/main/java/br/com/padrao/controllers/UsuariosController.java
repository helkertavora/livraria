package br.com.padrao.controllers;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.padrao.daos.UsuarioDao;
import br.com.padrao.models.Usuario;

@Controller
@Path("/usuarios")
public class UsuariosController {
	
	@Inject
	private UsuarioDao usuarioDao;
	@Inject
	private Result result;
	@Inject
	private Validator validator;
	
	@Path("/form")
	public void form(){}
	
	@Get("/{usuario.id}")
	public void form(Usuario usuario){
		if (usuarioDao.buscarPorId(usuario.getId()) == null) {
			result.notFound();
		} else {
			//result.include(usuarioEncontrado);
			result.include("usuario", usuarioDao.buscarPorId(usuario.getId()));
		}
		
	}
	
	@Path("/lista")
	public void lista(){
		result.include("usuarios", usuarioDao.listaTodos());
	}
	
	@Transactional
	@Delete("/remove")
	public void remove(Long id){
		Usuario usuario = usuarioDao.buscarPorId(id);
		 usuarioDao.remover(usuario);
		 result.include("notice", "Usuario deletado com sucesso!");
		 result.redirectTo(UsuariosController.class).lista();
	}
	
	@Transactional
	@Post("/salvar")
	public void salvar(@Valid Usuario usuario){
		validacoes(usuario);
		validator.onErrorRedirectTo(this).form();
		
		if(usuario.getId() != null){
			usuarioDao.atualizar(usuario);
			result.include("notice", "Usuario atualizado com sucesso!");
		}else{
			usuarioDao.adiciona(usuario);
			result.include("notice", "Usuario salvo com sucesso!");
		}
		result.redirectTo(this).lista();
	}
	
	public void validacoes(Usuario usuario) {
		if (usuario.getLogin() == null) {
			validator.add(new I18nMessage("dados invalidos", "usuario.login"));
		} else if (usuario.getNome() == null) {
			validator.add(new I18nMessage("dados invalidos", "usuario.nome"));
		}else if (usuario.getSenha() == null) {
			validator.add(new I18nMessage("dados invalidos", "usuario.senha"));
		}
	}

}