package com.devsu.cuentaapp.controller;

import com.devsu.cuentaapp.mapper.CuentaMapperImpl;
import com.devsu.cuentaapp.mapper.MovimientoMapperImpl;
import com.devsu.cuentaapp.util.testdata.controller.CuentaControllerTestData;
import com.devsu.cuentaapp.controller.client.CuentaRequest;
import com.devsu.cuentaapp.controller.client.CuentaResponse;
import com.devsu.cuentaapp.model.domain.Cuenta;
import com.devsu.cuentaapp.service.CuentaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebFluxTest
@ContextConfiguration(classes = {
        CuentaController.class,
        CuentaMapperImpl.class,
        MovimientoMapperImpl.class
    })
class CuentaControllerTest {

  @MockBean
  private CuentaService cuentaService;

  @Autowired
  private WebTestClient webTestClient;

  @Test
  @DisplayName("crear cuenta exitosamente")
  void crearCuentaExitosamente() {

    //Arrange
    Cuenta cuentaCreated = CuentaControllerTestData.getCuenta();
    when(cuentaService
        .crearCuenta(any()))
        .thenReturn(Mono.just(cuentaCreated));

    //Arrange
    FluxExchangeResult<CuentaResponse> result =  webTestClient.post()
        .uri("/api/cuentas")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(CuentaControllerTestData.getCuentaRequest()), CuentaRequest.class)
        .exchange()
        .expectStatus().isCreated()
        .returnResult(CuentaResponse.class);

    //Assert
    StepVerifier.create(result.getResponseBody())
        .expectNext(CuentaControllerTestData.getCuentaResponse())
        .expectNextCount(0)
        .thenCancel()
        .verify();
  }

}