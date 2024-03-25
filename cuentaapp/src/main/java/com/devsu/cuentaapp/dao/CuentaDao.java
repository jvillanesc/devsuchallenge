package com.devsu.cuentaapp.dao;

import com.devsu.cuentaapp.model.domain.Cuenta;
import com.devsu.cuentaapp.model.domain.Movimiento;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CuentaDao {

    Flux<Cuenta> buscarPorEstado(Short estado);

    public Mono<Boolean> existeCuenta(String nroCuenta);

    Mono<Cuenta> buscarPorNroCuenta(String nroCuenta);

    Mono<Cuenta> crearCuenta(Cuenta cuenta);

    Mono<Movimiento> registrarMovimiento(Movimiento movimiento);

    Mono<Cuenta> actualizarMontoInicial(String nroCuenta, Double saldoDisponible);

    Flux<Cuenta> desactivarCuentas(String identificacion);

    Flux<Cuenta> activarCuentas(String identificacion);

    Flux<Movimiento> obtenerMovimientos(String nroCuenta);

    Flux<Movimiento> obtenerMovimientos(String cliente, LocalDate fecha);
}
