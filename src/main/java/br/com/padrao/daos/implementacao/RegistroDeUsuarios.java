package br.com.padrao.daos.implementacao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.padrao.models.Usuario;

public class RegistroDeUsuarios {

	@Inject
    private EntityManager manager;
	
	public Usuario comLoginESenha(String login, String senha) {
		try {
			return this.manager.createQuery("select u from Usuario u where u.login=:login and u.senha=:senha", Usuario.class)
					.setParameter("login", login).setParameter("senha", senha).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
