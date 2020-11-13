package br.com.apirest.transacoes.handler;

	import org.springframework.http.HttpStatus;
	import org.springframework.http.converter.HttpMessageNotReadableException;
	import org.springframework.web.bind.annotation.ExceptionHandler;
	import org.springframework.web.bind.annotation.ResponseStatus;
	import org.springframework.web.bind.annotation.RestControllerAdvice;


	@RestControllerAdvice
	public class ErroConversaoHandler {

		@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
		@ExceptionHandler(HttpMessageNotReadableException.class)
		public  ErroRequestDto handle(HttpMessageNotReadableException exception) {
			 
			ErroRequestDto meuObjeto = new ErroRequestDto(exception.getMessage());
				
			return meuObjeto;
		}
	}


