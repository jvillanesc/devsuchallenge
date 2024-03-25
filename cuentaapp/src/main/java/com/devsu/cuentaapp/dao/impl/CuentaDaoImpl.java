package com.devsu.cuentaapp.dao.impl;

import com.devsu.cuentaapp.dao.CuentaDao;
import com.devsu.cuentaapp.mapper.CuentaMapper;
import com.devsu.cuentaapp.mapper.MovimientoMapper;
import com.devsu.cuentaapp.model.domain.Cuenta;
import com.devsu.cuentaapp.model.domain.Movimiento;
import com.devsu.cuentaapp.repository.CuentaRepository;
import com.devsu.cuentaapp.repository.MovimientoRepository;
import com.devsu.cuentaapp.repository.entity.CuentaEntity;
import com.devsu.cuentaapp.repository.entity.MovimientoEntity;
import com.devsu.cuentaapp.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
@Slf4j
public class CuentaDaoImpl implements CuentaDao {

    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    CuentaMapper cuentaMapper;

    @Autowired
    MovimientoMapper movimientoMapper;

    @Override
    public Flux<Cuenta> buscarPorEstado(Short status) {
        return cuentaRepository.findByEstado(status)
                .map(cuentaMapper::mapCuenta)
                .doOnError(error -> log.error("Error buscarPorEstado", error));
    }

    @Override
    public Mono<Cuenta> buscarPorNroCuenta(String nroCuenta) {
        return cuentaRepository.findByNroCuentaAndEstado(nroCuenta, Constant.CUENTA_ESTADO_ACTIVO)
                .map(cuentaMapper::mapCuenta)
                .doOnError(error -> log.error("Error buscarPorNroCuenta", error));
    }

    @Override
    public Mono<Boolean> existeCuenta(String code) {
        return cuentaRepository.existsByNroCuentaAndEstado(code, Constant.CUENTA_ESTADO_ACTIVO)
                .doOnError(error -> log.error("Error verificando existencia de cuenta", error));
    }

    @Override
    public Mono<Cuenta> crearCuenta(Cuenta cuenta) {
        return Mono.fromCallable(() -> cuentaMapper.mapCuentaEntity(cuenta))
                .flatMap(cuentaRepository::save)
                        .map(cuentaMapper::mapCuenta)
                .doOnError(error -> log.error("Error guardando movimiento"));
    }

    @Override
    public Mono<Movimiento> registrarMovimiento(Movimiento movimiento) {
        return Mono.fromCallable(() -> movimientoMapper.mapMovimientoEntity(movimiento))
                .zipWith(cuentaRepository.findByNroCuentaAndEstado(movimiento.getNroCuenta(), Constant.CUENTA_ESTADO_ACTIVO), (m, c) -> asignarIdCuenta(m, c))
                .flatMap(movimientoRepository::save)
                .map(movimientoMapper::mapMovimiento)
                .doOnError(error -> log.error("Error guardando movimiento"));
    }

    private MovimientoEntity asignarIdCuenta(MovimientoEntity movimientoEntity, CuentaEntity cuentaEntity) {
        movimientoEntity.setIdCuenta(cuentaEntity.getIdCuenta());
        return movimientoEntity;
    }

    @Override
    public Mono<Cuenta> actualizarMontoInicial(String nroCuenta, Double saldoDisponible) {
        return cuentaRepository.findByNroCuentaAndEstado(nroCuenta, Constant.CUENTA_ESTADO_ACTIVO)
                .map(cuenta -> asignarSaldoInicial(cuenta, saldoDisponible))
                .flatMap(cuentaRepository::save)
                .map(cuentaMapper::mapCuenta)
                .doOnError(error -> log.error("Error actualizando saldo inicial"));
    }

    @Override
    public Flux<Cuenta> desactivarCuentas(String identificacion) {
        return cuentaRepository.findByIdentificacionClienteAndEstado(identificacion, Constant.CUENTA_ESTADO_ACTIVO)
                .map(cuenta -> cambiarEstado(cuenta, Constant.CUENTA_ESTADO_INACTIVO))
                .flatMap(cuentaRepository::save)
                .map(cuentaMapper::mapCuenta)
                .doOnError(error -> log.error("Error actualizando saldo inicial"));
    }

    @Override
    public Flux<Cuenta> activarCuentas(String identificacion) {
        return cuentaRepository.findByIdentificacionClienteAndEstado(identificacion, Constant.CUENTA_ESTADO_INACTIVO)
                .map(cuenta -> cambiarEstado(cuenta, Constant.CUENTA_ESTADO_ACTIVO))
                .flatMap(cuentaRepository::save)
                .map(cuentaMapper::mapCuenta)
                .doOnError(error -> log.error("Error actualizando saldo inicial"));
    }

    @Override
    public Flux<Movimiento> obtenerMovimientos(String nroCuenta) {
        return cuentaRepository.findByNroCuentaAndEstado(nroCuenta, Constant.CUENTA_ESTADO_ACTIVO)
                .flatMapMany(cuentaEntity -> movimientoRepository.findByIdCuenta(cuentaEntity.getIdCuenta()))
                .map(movimiento -> movimientoMapper.mapMovimiento(movimiento));
    }

    @Override
    public Flux<Movimiento> obtenerMovimientos(String identificacion, LocalDate fecha) {
        return cuentaRepository.findByIdentificacionClienteAndEstado(identificacion, Constant.CUENTA_ESTADO_ACTIVO)
                .flatMap(cuentaEntity -> movimientoRepository.findByIdCuenta(cuentaEntity.getIdCuenta()))
                .filter(movimientoEntity -> movimientoEntity.getFecha().toLocalDate().equals(fecha))
                .map(movimiento -> movimientoMapper.mapMovimiento(movimiento));
    }

    private CuentaEntity asignarSaldoInicial(CuentaEntity cuenta, Double saldoDisponible) {
        cuenta.setSaldoInicial(saldoDisponible);
        return cuenta;
    }

    private CuentaEntity cambiarEstado(CuentaEntity cuenta, Short estado) {
        cuenta.setEstado(estado);
        return cuenta;
    }
}
