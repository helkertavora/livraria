package br.com.padrao.validacoes;

import javax.inject.Inject;

import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.padrao.daos.LivroDao;
import br.com.padrao.models.Livro;

public class LivroValidator {
	
	@Inject
	private LivroDao livroDao;
	@Inject
	private Validator validator;
	
	public void validate(Livro livro) {
		validator.validate(livro);
		if (livro.getId() == null) {
			if(livroDao.buscaPorIsbn(livro.getIsbn()) != null){
				validator.add(new I18nMessage("isbn", "isbn.duplicado"));
			}
		}
	}

	public <T> T onErrorRedirectTo(T controller) {
		return validator.onErrorRedirectTo(controller);
	}
	
	
}
