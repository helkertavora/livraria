package br.com.padrao.daos;

import java.util.List;

import br.com.padrao.models.Consumo;
import br.com.padrao.models.Contrato;

public interface ConsumoDao extends DaoGenerico<Consumo>  {
	
	public List<Consumo> buscaPorContrato(Contrato contrato);
	
}
