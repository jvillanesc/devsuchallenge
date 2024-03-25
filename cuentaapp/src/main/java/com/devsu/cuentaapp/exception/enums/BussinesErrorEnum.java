package com.devsu.cuentaapp.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BussinesErrorEnum {

    CUENTA_EXISTENTE("ERR-BS-001", "La cuenta ya existe"),
    CUENTA_NO_EXISTENTE("ERR-BS-002", "La cuenta no existe"),
    CLIENTE_NO_EXISTE("ERR-BS-002", "El cliente no exite"),
    MOVIMIENTO_NO_VALIDO("ERR-BS-002", "Movimiento no válido");

    private final String code;
    private final String message;

}
