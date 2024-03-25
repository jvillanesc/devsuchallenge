package com.devsu.cuentaapp.util.testdata.service;

import com.devsu.cuentaapp.model.domain.Cuenta;
import com.devsu.cuentaapp.thirdparty.client.ClienteResponse;

public class CuentaServiceTestData {
    public static Cuenta getCuenta() {
        return Cuenta.builder()
                .identificacionCliente("43163555")
                .nroCuenta("123545")
                .build();
    }

    public static ClienteResponse getCliente() {
        return ClienteResponse.builder()
                .identificacion("43163555")
                .nombre("Juan Perez")
                .edad((short) 25)
                .build();
    }

}
