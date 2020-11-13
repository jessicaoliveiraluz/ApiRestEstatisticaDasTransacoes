package br.com.apirest.transacoes.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirest.transacoes.model.Estatistica;
import br.com.apirest.transacoes.model.Transacao;
import br.com.apirest.transacoes.repository.TransacaoRepository;
import br.com.apirest.transacoes.service.CalculoDasEstatisticas;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/estatistica")
@Api(tags = "EstatisticaController", description = "Faz o cálculo das estatísticas das transações.")
public class EstatisticaController {
	@Autowired
	TransacaoRepository transacaoRepository;
	
	@Autowired
	CalculoDasEstatisticas estatisticaService;
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Devolve um objeto com as estatisticas das transacoes, com as informações:" + " total de transações, soma, média, valor mínimo, e valor máximo.")
	})
	@ApiOperation(value = "Retorna todas as transações realizadas nos últimos 60 segundos.")
	@GetMapping(produces = "application/json")
	public Estatistica buscarTransacoes() {
			
		List<Transacao> transacoesUltimoMinuto = transacaoRepository.buscarTransacoesUltimoMinuto();
		
		Estatistica estatisticaDeTransacoes = estatisticaService.calculoDasEstatisticas(transacoesUltimoMinuto);
		
		return estatisticaDeTransacoes;
	}

}

//data/hora corrente 23:08 -> 23:07 - 23:08