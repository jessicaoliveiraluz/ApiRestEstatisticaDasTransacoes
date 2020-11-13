package br.com.apirest.transacoes.integracao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class TestarApi {
	
	@Autowired
	private MockMvc mockMvc; 
	
	@Test
	public void CriandoTransacaoCorreta() throws Exception {
	
		String transacao = "{\r\n" + 
				"  \"dataHora\": \"2020-11-12T21:55:27.463Z\",\r\n" + 
				"  \"valor\": 100\r\n" + 
				"\r\n" + 
				"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/transacao")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).content(transacao))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void CriandoTransacaoIncorreta() throws Exception {
	
		String transacao = "{\r\n" + 
				"  \"dataHora\": \"2020-11-12T21:55:27.463Z\",\r\n" + 
				"  \"valor\": ABC\r\n" + 
				"\r\n" + 
				"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/transacao")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).content(transacao))
				.andExpect(MockMvcResultMatchers.status().is(422));
	}
	
	@Test
	public void deletarTransacaoIncorreta() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/transacao"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void BuscarTransacao() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/estatistica"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
