package br.com.padrao.daos.implementacao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.padrao.daos.UsuarioDao;
import br.com.padrao.models.Usuario;

@Transactional
public class UsuarioDaoIplm implements UsuarioDao{

	@Inject
    private EntityManager manager;
	
	@Override
	public List<Usuario> listaTodos() {
		return manager.createQuery("select u from Usuario u", Usuario.class).getResultList();
	}

	@Override
	public void adiciona(Usuario generico) {
		this.manager.persist(generico);
		
	}

	@Override
	public void atualizar(Usuario generico) {
		this.manager.merge(generico);
	}

	@Override
	public void remover(Usuario generico) {
		this.manager.remove(generico);
	}

	@Override
	public Usuario buscaPor(String nome) {
		return (Usuario) createCriteria().add(
				Restrictions.eq("isbn", nome));
	}

	@Override
	public Usuario buscarPorId(Long id) {
		return this.manager.find(Usuario.class, id);
	}

	@Override
	public Usuario buscaPorNome(String nome) {
		try {
			return this.manager.createQuery("select l from Usuario l where l.nome=:nome", Usuario.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	private Criteria createCriteria() {
		Session session = ((Session) manager.getDelegate());
		return session.createCriteria(Usuario.class);
	}

}
