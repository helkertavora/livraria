package br.com.padrao.UploadArquivos;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;

public class ArquivoDownload implements Download{

	
	private Arquivo arquivo;
	
	public ArquivoDownload(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public void write(HttpServletResponse response) throws IOException {
		Download download = new ByteArrayDownload(arquivo.getConteudo(),
				arquivo.getContentType(), arquivo.getNome());
				download.write(response);
	}
	

}
