package br.com.apirest.transacoes.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.apirest.transacoes.controller.form.TransacaoForm;
import br.com.apirest.transacoes.handler.ErroRequestDto;
import br.com.apirest.transacoes.model.Transacao;
import br.com.apirest.transacoes.repository.TransacaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/transacao")
@Api(tags = "TransacaoController", description = "Adiciona e remove todas as transações." )
@CrossOrigin(origins = "*")
public class TransacaoController {
	@Autowired
	TransacaoRepository transacaoRepository;
	
	
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Transação aceita", response = Object.class),
			@ApiResponse(code = 400, message = "Transação rescusada", response = ErroRequestDto.class, responseContainer = "List")
	})
	@ApiOperation(value = "Adiciona/Salva as novas transações no banco de dados")
	@PostMapping(produces = "application/json")
	public ResponseEntity<Transacao> salvar(@RequestBody @Valid TransacaoForm form , UriComponentsBuilder uriBuilder) {
		
		Transacao transacao = new Transacao(form.getValor() , form.getDataHora());
		
		transacaoRepository.salvarTransacao(transacao);
		
		URI uri = uriBuilder.path("/transacao").buildAndExpand().toUri();
		
		return ResponseEntity.created(uri).body(transacao);
	}
	
	@DeleteMapping(produces = "application/json")
	@ApiOperation(value = "Deleta todas as transações existentes")
	public void deletar() {
		transacaoRepository.deletarTransacoes();
	}

}