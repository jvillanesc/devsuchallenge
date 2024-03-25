package com.devsu.clienteapp.karate;

import com.devsu.clienteapp.controller.client.ClienteRequest;
import com.devsu.clienteapp.controller.client.ClienteResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClienteControllerIntegrationTest {

	String host;
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeAll
	void beforeAll() {
		System.out.println("Initializing test");
		host = "http://localhost:";
	}

	@Test
	@DisplayName("registro correcto cliente")
	void registroCorrectoCliente() {
		ClienteRequest clienteRequest = ClienteRequest.builder()
				.nombre("Juan Perez")
				.genero("M")
				.edad((short) 24)
				.identificacion("24586688")
				.direccion("Av. Manco Capac 156")
				.telefono("958366888")
				.build();

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ClienteRequest> request = new HttpEntity<>(clienteRequest, headers);

		ResponseEntity<ClienteResponse> clienteResponse = this.restTemplate
				.postForEntity(host + port + "/clientes", request, ClienteResponse.class);

		assertEquals("24586688", clienteResponse.getBody().getIdentificacion());
	}

	@Test
	@DisplayName("rechazo registro por datos incorrectos")
	void rechazoRegistroDatosIncorrectos() {
		ClienteRequest clienteRequest = ClienteRequest.builder()
				.nombre("Juan Perez")
				.genero("Z")
				.edad((short) 24)
				.identificacion("24586688")
				.direccion("Av. Manco Capac 156")
				.telefono("958366888")
				.build();

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ClienteRequest> request = new HttpEntity<>(clienteRequest, headers);

		ResponseEntity<ClienteResponse> clienteResponse = this.restTemplate
				.postForEntity(host + port + "/clientes", request, ClienteResponse.class);

		assertEquals(HttpStatus.BAD_REQUEST, clienteResponse.getStatusCode());
	}

}