package br.com.padrao.daos.implementacao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.padrao.daos.ContratoDao;
import br.com.padrao.models.Contrato;
import br.com.padrao.models.Lembrete;
import br.com.padrao.models.Usuario;

@Transactional
public class ContratoDaoIplm implements ContratoDao{

	@Inject
    private EntityManager manager;
	
	@Override
	public List<Contrato> listaTodos() {
		return manager.createQuery("select c from Contrato c", Contrato.class).getResultList();
	}

	@Override
	public void adiciona(Contrato generico) {
		this.manager.persist(generico);
		this.manager.flush();
		
	}

	@Override
	public void atualizar(Contrato generico) {
		this.manager.merge(generico);
		this.manager.flush();
	}

	@Override
	public void remover(Contrato generico) {
		this.manager.remove(generico);
	}

	@Override
	public Contrato buscaPor(String nome) {
		return (Contrato) createCriteria().add(
				Restrictions.eq("nome", nome));
	}

	@Override
	public Contrato buscarPorId(Long id) {
		return this.manager.find(Contrato.class, id);
	}

	@Override
	public List<Contrato> buscaPorUsuariosLista(Usuario usuario){
		try {
			return this.manager.createQuery("select c from Contrato c where c.usuario =:usuario", Contrato.class)
					.setParameter("usuario", usuario)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	@Override
	public Contrato buscaPorLembrete(Lembrete lembrete) {
		try {
			return this.manager.createQuery("select c from Contrato c where c.lembrete =: lembrete", Contrato.class)
					.setParameter("lembrete", lembrete)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	private Criteria createCriteria() {
		Session session = ((Session) manager.getDelegate());
		return session.createCriteria(Contrato.class);
	}
}
