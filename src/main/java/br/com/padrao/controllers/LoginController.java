package br.com.padrao.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.padrao.daos.implementacao.RegistroDeUsuarios;
import br.com.padrao.models.Usuario;
import br.com.padrao.validacoes.UsuarioLogado;

@Controller
@Path("/login")
public class LoginController {
	
	@Inject
	private RegistroDeUsuarios usuarios;
	@Inject
	private UsuarioLogado logado;
	@Inject
	private Result result;
	@Inject
	private Validator validator;
	
	@Get
	public void formulario() {}
	
	@Post("/autentica")
	public void login(String login, String senha) {
		
		Usuario usuario = usuarios.comLoginESenha(login, senha);
		if (usuario == null) {
			validator.add(new I18nMessage("usuario não encontrado", "login.ou.senha.invalidos"));
		}
		validator.onErrorRedirectTo(this).formulario();
		
		logado.loga(usuario);
		result.include("notice", "seja Bem vindo " + logado.getNome() + "!");
		result.redirectTo(LivrosController.class).lista();
	}

	@Post("/logout")
	public void logout() {
		logado.desloga();
		result.include("notice", "você está deslogado do sistema!");
		result.redirectTo(this).formulario();
	 }
}
