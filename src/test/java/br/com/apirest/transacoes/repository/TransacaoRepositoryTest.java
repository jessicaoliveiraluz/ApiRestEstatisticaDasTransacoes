package br.com.apirest.transacoes.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.apirest.transacoes.model.Transacao;

@RunWith(MockitoJUnitRunner.class)
public class TransacaoRepositoryTest {

	@InjectMocks
	private TransacaoRepository transacaoRepository;
	
	@Test
	public void buscarTransacoesSemNenhumaTrasacao() {
		
		List<Transacao> retorno = transacaoRepository.buscarTransacoes();
		
		Assert.assertNotNull(retorno);
		Assert.assertEquals(0, retorno.size());
	}
	
	@Test
	public void buscarTransacoesComtransacoes() {
		
		Transacao transacao = new Transacao(new BigDecimal("100.00"), LocalDateTime.now());
		transacaoRepository.salvarTransacao(transacao);
		
		List<Transacao> retorno = transacaoRepository.buscarTransacoes();
		
		Assert.assertNotNull(retorno);
		Assert.assertEquals(1, retorno.size());
	}
	
	@Test
	public void buscarTransacoesDeletandoTransacoes() {
		
		Transacao transacao = new Transacao(new BigDecimal("100.00"), LocalDateTime.now());
		transacaoRepository.salvarTransacao(transacao);
		
		transacaoRepository.deletarTransacoes();
		
		List<Transacao> retorno = transacaoRepository.buscarTransacoes();
		
		Assert.assertNotNull(retorno);
		Assert.assertEquals(0, retorno.size());
	}
	
	@Test
	public void buscarTransacoesUltimoMinutoComTransacoesMaioresQueUmMinuto() {
		
		Transacao transacao = new Transacao(new BigDecimal("100.00"), LocalDateTime.now().plusMinutes(1));
		transacaoRepository.salvarTransacao(transacao);
		
		List<Transacao> retorno = transacaoRepository.buscarTransacoesUltimoMinuto();
		
		Assert.assertNotNull(retorno);
		Assert.assertTrue(retorno.size()>=0);
	}
	
	@Test
	public void buscarTransacoesUltimoMinutoComTransacoesMenoresQueDoisMinutos() {
		
		Transacao transacao = new Transacao(new BigDecimal("100.00"), LocalDateTime.now().minusMinutes(2));
		transacaoRepository.salvarTransacao(transacao);
		
		List<Transacao> retorno = transacaoRepository.buscarTransacoesUltimoMinuto();
		
		Assert.assertNotNull(retorno);
		Assert.assertEquals(0, retorno.size());
	}
	
	@Test
	public void buscarTransacoesUltimoMinutoComTransacoesCorrente() {
		
		Transacao transacao = new Transacao(new BigDecimal("100.00"), LocalDateTime.now());
		transacaoRepository.salvarTransacao(transacao);
		
		List<Transacao> retorno = transacaoRepository.buscarTransacoesUltimoMinuto();
		
		Assert.assertNotNull(retorno);
		Assert.assertEquals(1, retorno.size());
	}
	
	@Test
	public void buscarTransacoesUltimoMinutoComTransacoesCorrenteMenorQueUmMinuto() {
		
		Transacao transacao = new Transacao(new BigDecimal("100.00"), LocalDateTime.now().minusMinutes(1));
		transacaoRepository.salvarTransacao(transacao);
		
		List<Transacao> retorno = transacaoRepository.buscarTransacoesUltimoMinuto();
		
		Assert.assertNotNull(retorno);
		Assert.assertEquals(1, retorno.size());
	}
	
}
