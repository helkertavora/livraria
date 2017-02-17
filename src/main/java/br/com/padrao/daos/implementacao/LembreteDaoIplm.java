package br.com.padrao.daos.implementacao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.padrao.daos.LembreteDao;
import br.com.padrao.models.Lembrete;
import br.com.padrao.models.Livro;

@Transactional
public class LembreteDaoIplm implements LembreteDao{

	@Inject
    private EntityManager manager;
	
	@Override
	public List<Lembrete> listaTodos() {
		return manager.createQuery("select l from Lembrete l", Lembrete.class).getResultList();
	}

	@Override
	public void adiciona(Lembrete generico) {
		this.manager.persist(generico);
		this.manager.flush();
		
	}

	@Override
	public void atualizar(Lembrete generico) {
		this.manager.merge(generico);
		this.manager.flush();
	}

	@Override
	public void remover(Lembrete generico) {
		this.manager.remove(generico);
	}

	@Override
	public Lembrete buscaPor(String isbn) {
		return null;
	}

	@Override
	public Lembrete buscarPorId(Long id) {
		return this.manager.find(Lembrete.class, id);
	}

	private Criteria createCriteria() {
		Session session = ((Session) manager.getDelegate());
		return session.createCriteria(Livro.class);
	}

}
