package br.com.padrao.daos;

import java.util.List;

import br.com.padrao.models.Contrato;
import br.com.padrao.models.Lembrete;
import br.com.padrao.models.Usuario;

public interface ContratoDao extends DaoGenerico<Contrato>  {
	
	public List<Contrato> buscaPorUsuariosLista(Usuario usuario);
	public Contrato buscaPorLembrete(Lembrete lembrete);
	
}
