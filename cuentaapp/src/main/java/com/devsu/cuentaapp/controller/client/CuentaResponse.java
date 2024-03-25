package com.devsu.cuentaapp.controller.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CuentaResponse {

  private String nroCuenta;

  private String tipoCuenta;

  private Double saldoInicial;

  private String identificacionCliente;

  private String nombreCliente;

  private Short estado;

}
