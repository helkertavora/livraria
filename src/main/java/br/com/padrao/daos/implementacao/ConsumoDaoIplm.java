package br.com.padrao.daos.implementacao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.padrao.daos.ConsumoDao;
import br.com.padrao.models.Consumo;
import br.com.padrao.models.Contrato;

@Transactional
public class ConsumoDaoIplm implements ConsumoDao{

	@Inject
    private EntityManager manager;
	
	@Override
	public List<Consumo> listaTodos() {
		return manager.createQuery("select c from Consumo c", Consumo.class).getResultList();
	}

	@Override
	public void adiciona(Consumo generico) {
		this.manager.persist(generico);
		
	}

	@Override
	public void atualizar(Consumo generico) {
		this.manager.merge(generico);
	}

	@Override
	public void remover(Consumo generico) {
		this.manager.remove(generico);
	}

	@Override
	public Consumo buscaPor(String isbn) {
		return null;
	}

	@Override
	public Consumo buscarPorId(Long id) {
		return this.manager.find(Consumo.class, id);
	}

	@Override
	public List<Consumo> buscaPorContrato(Contrato contrato) {
		try {
			return this.manager.createQuery("select c from Consumo c where c.contrato =: contrato", Consumo.class)
					.setParameter("contrato", contrato)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	private Criteria createCriteria() {
		Session session = ((Session) manager.getDelegate());
		return session.createCriteria(Consumo.class);
	}

}
