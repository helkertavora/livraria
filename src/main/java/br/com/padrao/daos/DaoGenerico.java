package br.com.padrao.daos;

import java.util.List;

public interface DaoGenerico<T> {
	
	public List<T> listaTodos();

	public void adiciona(T generico);

	public void atualizar(T generico);

	public void remover(T generico);

	public T buscaPor(String generico);

	public T buscarPorId(Long generico);
	
	
}
