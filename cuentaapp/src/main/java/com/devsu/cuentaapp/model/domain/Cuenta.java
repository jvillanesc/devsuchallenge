package com.devsu.cuentaapp.model.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Cuenta {

  private String nroCuenta;
  private String tipoCuenta;
  private Double saldoInicial;
  private String identificacionCliente;
  private String nombreCliente;
  private Short estado;

  public CuentaBuilder mutate() {
    return Cuenta.builder()
            .nroCuenta(nroCuenta)
            .tipoCuenta(tipoCuenta)
            .saldoInicial(saldoInicial)
            .identificacionCliente(identificacionCliente)
            .estado(estado);
  }

}
