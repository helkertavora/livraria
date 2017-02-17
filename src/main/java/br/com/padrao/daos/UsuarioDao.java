package br.com.padrao.daos;

import br.com.padrao.models.Usuario;

public interface UsuarioDao extends DaoGenerico<Usuario>  {
	
	public Usuario buscaPorNome(String nome);
	
}
