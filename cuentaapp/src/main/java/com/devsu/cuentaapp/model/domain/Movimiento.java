package com.devsu.cuentaapp.model.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Movimiento {

  private LocalDateTime fecha;
  private String nroCuenta;
  private String tipoMovimiento;
  private Double saldoInicial;
  private Double monto;
  private Double saldoDisponible;

  public MovimientoBuilder mutate() {
    return Movimiento.builder()
            .fecha(fecha)
            .nroCuenta(nroCuenta)
            .tipoMovimiento(tipoMovimiento)
            .saldoInicial(saldoInicial)
            .monto(monto)
            .saldoDisponible(saldoDisponible);
  }

}
