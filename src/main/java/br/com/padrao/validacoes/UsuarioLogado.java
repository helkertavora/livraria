package br.com.padrao.validacoes;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import br.com.padrao.models.Usuario;

@SessionScoped
public class UsuarioLogado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private String nome;

	public void loga(Usuario usuario) {
		this.nome = usuario.getNome();
		this.usuario = usuario;
	}

	public boolean isLogado() {
		return this.usuario != null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void desloga() {
		this.usuario = null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}