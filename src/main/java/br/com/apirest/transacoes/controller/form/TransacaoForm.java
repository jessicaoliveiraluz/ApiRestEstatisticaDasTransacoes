package br.com.apirest.transacoes.controller.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

public class TransacaoForm {
	
	@DecimalMin("0.00")
	@NotNull
	private BigDecimal valor;
	
	@NotNull 
	@PastOrPresent
	private LocalDateTime dataHora;
		
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
}
