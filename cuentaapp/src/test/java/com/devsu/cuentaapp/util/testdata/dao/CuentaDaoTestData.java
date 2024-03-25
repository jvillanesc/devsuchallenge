package com.devsu.cuentaapp.util.testdata.dao;

import com.devsu.cuentaapp.repository.entity.CuentaEntity;

public class CuentaDaoTestData {
    public static CuentaEntity getCuentaEntity() {
        return CuentaEntity.builder()
                .identificacionCliente("43163555")
                .nroCuenta("123545")
                .build();
    }

}
