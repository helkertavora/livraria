package br.com.padrao.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.view.Results;
import br.com.padrao.UploadArquivos.Arquivo;
import br.com.padrao.UploadArquivos.ArquivoDownload;
import br.com.padrao.UploadArquivos.Diretorio;
import br.com.padrao.daos.LivroDao;
import br.com.padrao.models.Livro;
import br.com.padrao.models.enums.Moeda;
import br.com.padrao.validacoes.LivroValidator;

import com.google.common.io.ByteStreams;

@Controller
@Path("/livros")
public class LivrosController {
	
	@Inject
	private LivroDao livroDao;
	@Inject
	private Result result;
	@Inject
	private LivroValidator livroValidator;
	@Inject
	private Diretorio imagens;
	
	@Get("/form")
	public void form(){
		result.include("moedas", Moeda.values());
	}
	@Get("/{livro.id}")
	public void form(Livro livro){
		result.include("livro", livroDao.buscarPorId(livro.getId()));
		result.include("moedas", Moeda.values());
	}
	
	@Path("/lista")
	public void lista(){
		result.include("listaLivros", livroDao.listaTodos());
	}
	
	@Transactional
	@Delete("/remove")
	public void remove(Long id){
		Livro livro = livroDao.buscarPorId(id);
		 livroDao.remover(livro);
		 result.include("notice", "Livro deletado com sucesso!!");
		 result.redirectTo(this).lista();
	}
	
	@Get("/capa/{isbn}")
	public Download capa(String isbn){
		Livro livro = livroDao.buscaPorIsbn(isbn);
		Arquivo capa = imagens.recupera(livro.getCapa());
		if (capa == null) {
			result.use(Results.http()).body("Sem Capa!");
			return null;
			}
		return new ArquivoDownload(capa);
	}
	
	public void edita(String isbn) {
		Livro livroEncontrado = livroDao.buscaPorIsbn(isbn);
		if (livroEncontrado == null) {
			result.notFound();
		} else {
			result.include(livroEncontrado);
			result.of(this).form();
		}
}
	
	@Transactional
	@Post("/salvar")
	public void salvar(@Valid Livro livro, UploadedFile capa)throws IOException{
		if(livro.getId() != null){
			livroValidator.validate(livro);
			livroValidator.onErrorRedirectTo(this).form(livro);
			
			if (capa != null) {
				URI imagemCapa = imagens.grava(new Arquivo(
				capa.getFileName(),
				ByteStreams.toByteArray(capa.getFile()),
				capa.getContentType(),
				Calendar.getInstance()));
				livro.setCapa(imagemCapa);
				}
			livroDao.atualizar(livro);
			result.include("notice", "Livro atualizado com sucesso!");		
		}else{
				livroValidator.validate(livro);
				livroValidator.onErrorRedirectTo(this).form();
				if (capa != null) {
					URI imagemCapa = imagens.grava(new Arquivo(
					capa.getFileName(),
					ByteStreams.toByteArray(capa.getFile()),
					capa.getContentType(),
					Calendar.getInstance()));
					livro.setCapa(imagemCapa);
					}
				livroDao.adiciona(livro);
		result.include("notice", "Livro salvo com sucesso!");
		//result.use(Results.http()).body("Deu tudo certo");
		}
		result.redirectTo(this).lista();
	}
	
	@Path("/emJson")
	public void emJson(){
		result.use(Results.json()).from(livroDao.listaTodos()).serialize();
		//result.use(Results.json()).from(livroDao.listaTodos()).include("preco").serialize();
	}
	
	@Path("/emXml")
	public void emXml(){
		result.use(Results.xml()).from(livroDao.listaTodos()).serialize();
		//result.use(Results.xml()).from(livroDao.listaTodos()).include("preco").serialize();
	}
	
}