package com.devsu.cuentaapp.repository;

import com.devsu.cuentaapp.repository.entity.CuentaEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CuentaRepository extends R2dbcRepository<CuentaEntity, Integer>{

  Flux<CuentaEntity> findByEstado(Short status);

  Flux<CuentaEntity> findByIdentificacionClienteAndEstado(String identificacion, Short estado);

  Mono<CuentaEntity> findByNroCuentaAndEstado(String nroCuenta, Short estado);

  Mono<Boolean> existsByNroCuentaAndEstado(String nroCuenta , Short estado);

}
