package br.com.apirest.transacoes.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.apirest.transacoes.model.Transacao;

@Repository
public class TransacaoRepository {
	public List<Transacao> listaDeTransacoes = new ArrayList<Transacao>();
	
	public synchronized List<Transacao> buscarTransacoes() {
		return listaDeTransacoes;
	}
	
	public synchronized List<Transacao> buscarTransacoesUltimoMinuto() {	
		
		List<Transacao> listaTransacaoUltimoMinuto = new ArrayList<Transacao>();
		
		//corrente 23:35
		
		LocalDateTime correnteMais1Min = LocalDateTime.now().plusMinutes(1);
		LocalDateTime correnteMenos2Min = LocalDateTime.now().minusMinutes(2);
		
		listaTransacaoUltimoMinuto = listaDeTransacoes.stream()
								.filter(transacao -> transacao.getDataHora().isAfter(correnteMenos2Min) && transacao.getDataHora().isBefore(correnteMais1Min))
								.collect(Collectors.toList());
		
		return listaTransacaoUltimoMinuto;
	}
	
	public synchronized void salvarTransacao(Transacao transacao) {
		listaDeTransacoes.add(transacao);
	}
	
	public synchronized void deletarTransacoes() {
		listaDeTransacoes = new ArrayList<Transacao>();
	}
	
}