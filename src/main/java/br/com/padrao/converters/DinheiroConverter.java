package br.com.padrao.converters;

import java.math.BigDecimal;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;
import br.com.padrao.models.Dinheiro;
import br.com.padrao.models.enums.Moeda;

@Convert(Dinheiro.class)
public class DinheiroConverter implements Converter<Dinheiro> {
	
	@Override
	public Dinheiro convert(String value, Class<? extends Dinheiro> type) {
		return new Dinheiro(Moeda.REAL, new BigDecimal("1.00"));
	}
}