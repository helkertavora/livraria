package br.com.padrao.daos;

import br.com.padrao.models.Livro;

public interface LivroDao extends DaoGenerico<Livro>  {
	
	public Livro buscaPorIsbn(String isbn);
	
}
