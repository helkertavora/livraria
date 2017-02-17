package br.com.padrao.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.padrao.daos.ConsumoDao;
import br.com.padrao.daos.ContratoDao;
import br.com.padrao.daos.LembreteDao;
import br.com.padrao.models.Consumo;
import br.com.padrao.models.Contrato;
import br.com.padrao.models.Lembrete;
import br.com.padrao.models.Usuario;
import br.com.padrao.validacoes.UsuarioLogado;

@Controller
@Path("/contrato")
public class ContratoController {
	
	@Inject private UsuarioLogado logado;
	@Inject private ContratoDao contratoDao;
	@Inject private ConsumoDao consumoDao;
	@Inject private LembreteDao lembreteDao;
	@Inject private Result result;
	@Inject private Validator validator;
	
	@Get("/form")
	public void form(){}
	
	@Get("/{contrato.id}")
	public void form(Contrato contrato){
		result.include("contrato", contratoDao.buscarPorId(contrato.getId()));
	}
	
	@Path("/lista")
	public void lista(){
		Usuario usu = logado.getUsuario();
		List<Contrato> listaDeContratos =contratoDao.buscaPorUsuariosLista(usu);
		if (!listaDeContratos.isEmpty()){
			for (Contrato contrato : listaDeContratos) {
				if (contrato.tresMesesParaFimDeContrato() && contrato.getLembrete().isAtivo()) {
						result.include("notice", "<h3><b>"+contrato.getLembrete().getDescricao()+"</b><h3> "
								+ "Nome do contrato:<b> "+contrato.getNome()+" </b>");	
				}
			}
		}
		result.include("consumos", consumoDao.listaTodos());
		result.include("listaContratos", contratoDao.listaTodos());
	}
	
	@Get("/busca-contrato/{id}")
	public void buscarContrato(Long id){
		Contrato contrato = contratoDao.buscarPorId(id);
		result.use(Results.json()).from(contrato).serialize();
	}
	
	@Get("/editar-cunsumo/{id}")
	public void editarConsumo(Long id){
		Consumo consumo = consumoDao.buscarPorId(id);
		result.use(Results.json()).from(consumo).include("contrato").serialize();
	}
	
	@Transactional
	@Post("/salvar-consumo")
	public void salvarConsumo(Consumo consumo){
		salvaOuAtualizaConsumo(consumo);
		result.nothing();
	}
	
	public void salvaOuAtualizaConsumo(Consumo consumo){
		Contrato contrato = contratoDao.buscarPorId(consumo.getContrato().getId());
		if (consumo.getId() == null) {
			consumo.setContrato(contrato);
			consumoDao.adiciona(consumo);
			result.include("notice", "Consumo Salvo com sucesso!");
		} else {
			consumo.setContrato(contrato);
			consumoDao.atualizar(consumo);
			result.include("notice", "Consumo Alterado com sucesso!");
		}
		result.redirectTo(this).lista();
	}
	
	@Transactional
	@Post("/lembrete/ativacao/{id}")
	public void ativaLembrete(Long id){
		Contrato contrato = contratoDao.buscarPorId(id);
		if (contrato.getLembrete().isAtivo() == true) {
			contrato.getLembrete().setAtivo(false);
		} else {
			contrato.getLembrete().setAtivo(true);
		}
		salvarOuAtualizar(contrato);
		result.nothing();
	}
	
	@Transactional
	@Delete("/remove")
	public void remove(Long id){
		Contrato contrato = contratoDao.buscarPorId(id);
		 contratoDao.remover(contrato);
		 result.include("notice", "Contrato deletado com sucesso!!");
		 result.redirectTo(this).lista();
	}
	
	@Transactional
	@Delete("/consumo/excluir")
	public void removeConsumo(Long id){
		Consumo consumo = consumoDao.buscarPorId(id);
		 consumoDao.remover(consumo);
		 result.include("notice", "Consumo deletado com sucesso!!");
		 result.redirectTo(this).lista();
	}
	
	public void edita(Long id) {
		Contrato contratoEncontrado = contratoDao.buscarPorId(id);
		if (contratoEncontrado == null) {
			result.notFound();
		} else {
			result.include(contratoEncontrado);
			result.of(this).form();
		}
}
	
	@Transactional
	@Post("/salvar")
	public void salvar(Contrato contrato){
			validator.validate(contrato);
			validator.onErrorRedirectTo(this).form(contrato);
			contrato.setUsuario(logado.getUsuario());
			salvarOuAtualizar(contrato);
			//result.use(Results.http()).body("Deu tudo certo");
			result.redirectTo(this).lista();
		}	
	
	public void salvarOuAtualizar(Contrato contrato){
		if (contrato.getId() == null) {
			contratoDao.adiciona(contrato);
			Lembrete lembrete = new Lembrete();
			lembrete.setDescricao("contrato esta proximo ao vencimento!");
			lembrete.setAtivo(true);
			lembrete.setId(contrato.getId());
			lembreteDao.adiciona(lembrete);
			Contrato contratoResult = contratoDao.buscarPorId(contrato.getId());
			contratoResult.setLembrete(lembrete);
			contratoDao.atualizar(contratoResult);
			result.include("notice", "Contrato salvo com sucesso!");
		} else {
			contrato.setLembrete(lembreteDao.buscarPorId(contrato.getId()));
			contratoDao.atualizar(contrato);
			result.include("notice", "Contrato Atualizado com sucesso!");
		}
	}
}