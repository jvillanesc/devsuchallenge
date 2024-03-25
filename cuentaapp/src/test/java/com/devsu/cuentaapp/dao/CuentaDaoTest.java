package com.devsu.cuentaapp.dao;

import com.devsu.cuentaapp.dao.impl.CuentaDaoImpl;
import com.devsu.cuentaapp.repository.CuentaRepository;
import com.devsu.cuentaapp.mapper.CuentaMapper;
import com.devsu.cuentaapp.model.domain.Cuenta;
import com.devsu.cuentaapp.util.testdata.dao.CuentaDaoTestData;
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
class CuentaDaoTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @Spy
    private CuentaMapper cuentaMapper = Mappers.getMapper(CuentaMapper.class);

    @InjectMocks
    private CuentaDaoImpl cuentaDao;

    @Test
    @DisplayName("creacion de cuenta exitoso")
    void creacionCuentaExitoso() {

        //Arrange
        Mockito.when(cuentaRepository.save(Mockito.any()))
                .thenReturn(Mono.just(CuentaDaoTestData.getCuentaEntity()));
        Cuenta newCuenta = CuentaServiceTestData.getCuenta();

        //Act
        Mono<Cuenta> mono = cuentaDao.crearCuenta(newCuenta);

        //Assert
        StepVerifier.create(mono)
                    .assertNext(cuenta -> Assertions.assertThat(cuenta.getIdentificacionCliente()).isEqualTo(newCuenta.getIdentificacionCliente()))
                    .verifyComplete();
    }


}
