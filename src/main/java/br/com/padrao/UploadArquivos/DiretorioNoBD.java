package br.com.padrao.UploadArquivos;

import java.net.URI;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class DiretorioNoBD implements Diretorio{
	@Inject
	private EntityManager manager;
	
	@Override
	public URI grava(Arquivo arquivo) {
		manager.persist(arquivo);
		return URI.create("bd://" + arquivo.getId());
	}
	@Override
	public Arquivo recupera(URI chave) {
		if(chave == null) return null;
		if(!chave.getScheme().equals("bd")){
			throw new IllegalArgumentException(chave + " não é uma URI de banco de dados");
		}
		Long id = Long.valueOf(chave.getAuthority());
		return manager.find(Arquivo.class, id);
	}

}
