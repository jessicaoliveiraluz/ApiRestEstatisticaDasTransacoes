package br.com.apirest.transacoes.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.apirest.transacoes.model.Estatistica;
import br.com.apirest.transacoes.model.Transacao;

@RunWith(MockitoJUnitRunner.class)
public class CalculoDasEstatisticasTest {

	@InjectMocks
	private CalculoDasEstatisticas calculoDasEstatisticas;
	
	@Test
	public void calculoDasEstatisticasPassandoUmaListaVazia() {
		
		List<Transacao> listaVazia = new ArrayList<>();
		Estatistica retorno = calculoDasEstatisticas.calculoDasEstatisticas(listaVazia);
		
		Assert.assertNotNull(retorno);
		Assert.assertEquals(0, retorno.getCount());
		Assert.assertEquals(BigDecimal.ZERO, retorno.getAvg());
		Assert.assertEquals(BigDecimal.ZERO, retorno.getMax());
		Assert.assertEquals(BigDecimal.ZERO, retorno.getMin());
		Assert.assertEquals(BigDecimal.ZERO, retorno.getSum());
	}
	
	@Test
	public void calculoDasEstatisticasPassandoUmaListaPreenchidaComValorMaiorQueZero() {
		
		Transacao transacao1 = new Transacao(new BigDecimal("100.00"), LocalDateTime.now());
		Transacao transacao2 = new Transacao(new BigDecimal("200.00"), LocalDateTime.now());
		List<Transacao> listaPreenchida = new ArrayList<>();
		listaPreenchida.add(transacao1);
		listaPreenchida.add(transacao2);
		Estatistica retorno = calculoDasEstatisticas.calculoDasEstatisticas(listaPreenchida);
		
		Assert.assertNotNull(retorno);
		Assert.assertEquals(2, retorno.getCount());
		Assert.assertEquals(new BigDecimal("150.00"), retorno.getAvg());
		Assert.assertEquals(new BigDecimal("200.00"), retorno.getMax());
		Assert.assertEquals(new BigDecimal("100.00"), retorno.getMin());
		Assert.assertEquals(new BigDecimal("300.00"), retorno.getSum());
	}
	
	@Test
	public void calculoDasEstatisticasPassandoUmListaPreechidaComValorMenorQueZero() {
		
		Transacao transacao1 = new Transacao(new BigDecimal("200.00"), LocalDateTime.now());
		Transacao transacao2 = new Transacao(new BigDecimal("100.00"), LocalDateTime.now());
		List<Transacao> listaPreenchida = new ArrayList<>();
		listaPreenchida.add(transacao1);
		listaPreenchida.add(transacao2);
		Estatistica retorno = calculoDasEstatisticas.calculoDasEstatisticas(listaPreenchida);
		
		Assert.assertNotNull(retorno);
		Assert.assertEquals(2, retorno.getCount());
		Assert.assertEquals(new BigDecimal("150.00"), retorno.getAvg());
		Assert.assertEquals(new BigDecimal("200.00"), retorno.getMax());
		Assert.assertEquals(new BigDecimal("100.00"), retorno.getMin());
		Assert.assertEquals(new BigDecimal("300.00"), retorno.getSum());
	}
	
}
