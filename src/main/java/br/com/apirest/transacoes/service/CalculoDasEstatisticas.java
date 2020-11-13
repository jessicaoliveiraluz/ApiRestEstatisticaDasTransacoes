package br.com.apirest.transacoes.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.apirest.transacoes.model.Estatistica;
import br.com.apirest.transacoes.model.Transacao;

@Service
public class CalculoDasEstatisticas {
	
	public Estatistica calculoDasEstatisticas(List<Transacao> listagem) {
		int count = 0;
		BigDecimal sum = BigDecimal.ZERO;
		BigDecimal avg = BigDecimal.ZERO;
		BigDecimal min = BigDecimal.ZERO;
		BigDecimal max = BigDecimal.ZERO;
		
		for(Transacao transacao:listagem) {
			count = count + 1;
			sum = sum.add(transacao.getValor());
			
			if(min == BigDecimal.ZERO) {
				min = transacao.getValor();
				 
			} else {
				
				if(transacao.getValor().compareTo(min) < 0) {
					min = transacao.getValor();
				}
			}
			
			if(transacao.getValor().compareTo(max) > 0) {
				max = transacao.getValor();
			}
		}
		
		if(count > 0) {
		avg = (sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP));
		}
		
		return new Estatistica(count, sum, avg, min, max); 
		
	}

}
