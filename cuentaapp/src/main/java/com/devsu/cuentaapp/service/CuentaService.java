package com.devsu.cuentaapp.service;

import com.devsu.cuentaapp.dao.CuentaDao;
import com.devsu.cuentaapp.dto.OperacionDto;
import com.devsu.cuentaapp.exception.BussinesException;
import com.devsu.cuentaapp.exception.enums.BussinesErrorEnum;
import com.devsu.cuentaapp.model.domain.Cuenta;
import com.devsu.cuentaapp.model.domain.Movimiento;
import com.devsu.cuentaapp.thirdparty.ClienteServiceClient;
import com.devsu.cuentaapp.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
public class CuentaService {

  @Autowired
  CuentaDao cuentaDao;

  @Autowired
  ClienteServiceClient clienteClient;

  public Flux<Cuenta> obtenerCuentasActivas() {
    log.info("Obtener cuentas activas");
    return cuentaDao.buscarPorEstado(Constant.CUENTA_ESTADO_ACTIVO)
            .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<Cuenta> crearCuenta(final Cuenta cuenta) {
    log.info("Crear cuenta {}", cuenta.getNroCuenta());
    return cuentaDao.existeCuenta(cuenta.getNroCuenta())
            .flatMap(exists ->
                    Boolean.TRUE.equals(exists)
                            ? Mono.error(new BussinesException(BussinesErrorEnum.CUENTA_EXISTENTE))
                            :
                            clienteClient.obtenerCliente(cuenta.getIdentificacionCliente())
                                    .flatMap(clienteResponse -> cuentaDao.crearCuenta(
                                            cuenta.mutate()
                                            .estado(Constant.CUENTA_ESTADO_ACTIVO)
                                            .nombreCliente(clienteResponse.getNombre())
                                            .build()
                                            )
                                    )
            );
  }

    public Mono<Cuenta> obtenerCuenta(String nroCuenta) {
      log.info("Obtener cuenta {}", nroCuenta);
      return cuentaDao.buscarPorNroCuenta(nroCuenta)
              .switchIfEmpty(Mono.error(new BussinesException(BussinesErrorEnum.CUENTA_NO_EXISTENTE)));
    }

  public Mono<Movimiento> registrarMovimiento(String nroCuenta, Movimiento movimiento) {
    log.info("Registrar movimiento {} {}", nroCuenta, movimiento.getTipoMovimiento());
    return obtenerCuenta(nroCuenta)
            .map(cuenta -> validarFactibilidadOperacion(cuenta, movimiento))
            .flatMap(cuenta -> cuentaDao.registrarMovimiento(
                    movimiento.mutate()
                    .fecha(LocalDateTime.now())
                    .nroCuenta(nroCuenta)
                    .saldoInicial(cuenta.getSaldoInicial())
                    .saldoDisponible(movimiento.getTipoMovimiento().equals("D")
                            ?
                              cuenta.getSaldoInicial() + movimiento.getMonto()
                            :
                              cuenta.getSaldoInicial() - movimiento.getMonto())
                    .build())
                    .flatMap(movimiento1 -> cuentaDao.actualizarMontoInicial(nroCuenta, movimiento1.getSaldoDisponible())
                            .map(cuenta1 -> movimiento1)));
  }

  private Cuenta validarFactibilidadOperacion(Cuenta cuenta, Movimiento movimiento) {
    if (movimiento.getTipoMovimiento().equals("R") &&
        cuenta.getSaldoInicial() - movimiento.getMonto() < 0)
          throw new BussinesException(BussinesErrorEnum.MOVIMIENTO_NO_VALIDO);
    return cuenta;
  }

  public Flux<OperacionDto> operarCuentasCliente(OperacionDto operacionDto) {
    log.info("Operar cuentas cliente {} {}", operacionDto.getIdentificacion(), operacionDto.getTipoOperacion());
    if (operacionDto.getTipoOperacion().equals("B"))
        return cuentaDao.desactivarCuentas(operacionDto.getIdentificacion())
                .map(cuenta -> operacionDto);
    else
        return cuentaDao.activarCuentas(operacionDto.getIdentificacion())
                .map(cuenta -> operacionDto);
  }

    public Flux<Movimiento> obtenerMovimientos(String nroCuenta) {
      log.info("Obtener movimientos numero cuenta {}", nroCuenta);
      return cuentaDao.obtenerMovimientos(nroCuenta);
    }

    public Flux<Movimiento> obtenerMovimientos(String identificacion, LocalDate fecha) {
      log.info("Obtener movimientos busqueda {} {}", identificacion, fecha.toString());
      return cuentaDao.obtenerMovimientos(identificacion, fecha);
    }
}
