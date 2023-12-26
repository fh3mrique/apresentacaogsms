package com.gcms.fluflycats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gcms.fluflycats.controllers.GatoAPI;
import com.gcms.fluflycats.entities.Gato;
import com.gcms.fluflycats.repository.GatoRepository;

@SpringBootTest
class FluflycatsApplicationTests {

	 @Mock
	 private GatoRepository gatoRepository;

	 @InjectMocks
	 private GatoAPI gatoAPI;

	    @Test
	    public void testFindAll() {
	        // Criação de alguns gatos fictícios para simular o retorno do repositório
	        Gato gato1 = new Gato(1L, "NomeGato1", "URL1");
	        Gato gato2 = new Gato(2L, "NomeGato2", "URL2");
	        List<Gato> gatos = Arrays.asList(gato1, gato2);

	        // Configurando o comportamento do repositório mock
	        when(gatoRepository.findAll()).thenReturn(gatos);

	        // Chamando o método do controlador
	        ResponseEntity<List<Gato>> responseEntity = gatoAPI.findall();

	        // Verificando se a resposta é bem-sucedida (status HTTP 200 OK)
	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	        // Verificando se a lista de gatos na resposta é a mesma que configuramos
	        assertEquals(gatos, responseEntity.getBody());
	    }

}
