package br.com.padrao.daos.implementacao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.padrao.daos.LivroDao;
import br.com.padrao.models.Livro;

@Transactional
public class LivroDaoIplm implements LivroDao{

	@Inject
    private EntityManager manager;
	
	@Override
	public List<Livro> listaTodos() {
		return manager.createQuery("select c from Livro c", Livro.class).getResultList();
	}

	@Override
	public void adiciona(Livro generico) {
		this.manager.persist(generico);
		
	}

	@Override
	public void atualizar(Livro generico) {
		this.manager.merge(generico);
	}

	@Override
	public void remover(Livro generico) {
		this.manager.remove(generico);
	}

	@Override
	public Livro buscaPor(String isbn) {
		return (Livro) createCriteria().add(
				Restrictions.eq("isbn", isbn));
	}

	@Override
	public Livro buscarPorId(Long id) {
		return this.manager.find(Livro.class, id);
	}

	@Override
	public Livro buscaPorIsbn(String isbn) {
		try {
			return this.manager.createQuery("select l from Livro l where l.isbn=:isbn", Livro.class)
					.setParameter("isbn", isbn).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	private Criteria createCriteria() {
		Session session = ((Session) manager.getDelegate());
		return session.createCriteria(Livro.class);
	}

}
