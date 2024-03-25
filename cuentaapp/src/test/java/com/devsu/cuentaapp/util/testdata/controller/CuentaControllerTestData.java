package com.devsu.cuentaapp.util.testdata.controller;

import com.devsu.cuentaapp.controller.client.CuentaRequest;
import com.devsu.cuentaapp.controller.client.CuentaResponse;
import com.devsu.cuentaapp.model.domain.Cuenta;

public class CuentaControllerTestData {
    public static Cuenta getCuenta() {
        return Cuenta.builder()
                .identificacionCliente("43163555")
                .saldoInicial(30.5)
                .tipoCuenta("C")
                .nroCuenta("123545")
                .build();
    }

    public static CuentaRequest getCuentaRequest() {
        return CuentaRequest.builder()
                .identificacionCliente("43163555")
                .saldoInicial(30.5)
                .tipoCuenta("C")
                .nroCuenta("123545")
                .build();
    }

    public static CuentaResponse getCuentaResponse() {
        return CuentaResponse.builder()
                .identificacionCliente("43163555")
                .saldoInicial(30.5)
                .tipoCuenta("C")
                .nroCuenta("123545")
                .build();
    }

}
