package com.devsu.cuentaapp.service;

import com.devsu.cuentaapp.dao.CuentaDao;
import com.devsu.cuentaapp.exception.BussinesException;
import com.devsu.cuentaapp.mapper.CuentaMapper;
import com.devsu.cuentaapp.model.domain.Cuenta;
import com.devsu.cuentaapp.thirdparty.ClienteServiceClient;
import com.devsu.cuentaapp.util.testdata.service.CuentaServiceTestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
@ExtendWith({MockitoExtension.class})
class CuentaServiceTest {

    @Mock
    private CuentaDao cuentaDao;

    @Mock
    private ClienteServiceClient clienteServiceClient;

    @Spy
    private CuentaMapper documentMapper = Mappers.getMapper(CuentaMapper.class);

    @InjectMocks
    private CuentaService cuentaService;

    @Test
    @DisplayName("creacion de cuenta exitoso")
    void creacionCuentaExitoso() {

        //Arrange
        Mockito.when(cuentaDao.existeCuenta(Mockito.any()))
                .thenReturn(Mono.just(Boolean.FALSE));
        Mockito.when(cuentaDao.crearCuenta(Mockito.any()))
                .thenReturn(Mono.just(CuentaServiceTestData.getCuenta()));
        Mockito.when(clienteServiceClient.obtenerCliente(Mockito.any()))
                .thenReturn(Mono.just(CuentaServiceTestData.getCliente()));
        Cuenta newCuenta = CuentaServiceTestData.getCuenta();

        //Act
        Mono<Cuenta> mono = cuentaService.crearCuenta(newCuenta);

        //Assert
        StepVerifier.create(mono)
                    .assertNext(cuenta -> Assertions.assertThat(cuenta.getIdentificacionCliente()).isEqualTo(newCuenta.getIdentificacionCliente()))
                    .verifyComplete();
    }


}
