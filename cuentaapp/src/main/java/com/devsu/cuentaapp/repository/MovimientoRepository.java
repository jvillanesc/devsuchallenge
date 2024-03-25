package com.devsu.cuentaapp.repository;

import com.devsu.cuentaapp.repository.entity.CuentaEntity;
import com.devsu.cuentaapp.repository.entity.MovimientoEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MovimientoRepository extends R2dbcRepository<MovimientoEntity, Integer>{

    Flux<MovimientoEntity> findByIdCuenta(Integer idCuenta);
}
