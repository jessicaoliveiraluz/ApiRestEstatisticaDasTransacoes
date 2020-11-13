package br.com.apirest.transacoes.handler;

public class ErroRequestDto {

	public ErroRequestDto(String mensagem) {
		
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	private String mensagem;
}