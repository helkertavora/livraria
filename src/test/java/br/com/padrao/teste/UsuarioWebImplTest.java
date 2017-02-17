package br.com.padrao.teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.padrao.models.Usuario;
import br.com.padrao.validacoes.UsuarioLogado;

public class UsuarioWebImplTest {

	private UsuarioLogado usuarioWeb;

	@Before
	public void setUp() throws Exception {
		usuarioWeb = new UsuarioLogado();
	}

	@Test
	public final void deveAutenticarUmUsuario() {
		Usuario usuario = new Usuario();
		usuario.setLogin("emanuelcanuto@gmail.com");
		usuario.setSenha("123");
		usuarioWeb.loga(usuario);
		assertEquals(true, usuarioWeb.isLogado());
	}

	@Test
	public final void deveDeslogarUmUsuario() {
		Usuario usuario = new Usuario();
		usuario.setLogin("emanuelcanuto@gmail.com");
		usuario.setSenha("123");
		usuarioWeb.loga(usuario);
		usuarioWeb.desloga();
		assertEquals(false, usuarioWeb.isLogado());
	}

}
