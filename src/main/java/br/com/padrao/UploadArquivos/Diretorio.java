package br.com.padrao.UploadArquivos;

import java.net.URI;

public interface Diretorio {
	
	URI grava(Arquivo arquivo);

	Arquivo recupera(URI chave);
}