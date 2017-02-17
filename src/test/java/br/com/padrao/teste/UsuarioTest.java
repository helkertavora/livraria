package br.com.padrao.teste;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.padrao.models.Usuario;

public class UsuarioTest {

	@Test
	public final void deveAutenticarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setSenha("123");
		
		Usuario usuario2 = new Usuario();
		usuario2.setSenha("123");
		
		assertTrue(usuario.autenticaUsuario(usuario2.getSenha()));
		
	}

	@Test
	public final void deveCriptografarSenha() {
		Usuario usuario = new Usuario();
		usuario.setSenha("123");
		assertEquals("ICy5YqxZB1uWSwcVLSNLcA==", usuario.getSenha());
	}	
}
