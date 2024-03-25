package com.devsu.clienteapp.error.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BussinesErrorEnum {

    CLIENTE_NO_EXISTE("BS-ERR-001", "Cliente no existe"),
    CLIENTE_EXISTE("BS-ERR-001", "Cliente ya existe"),
    CONSTRASENA_INCORRECTA("BS-ERR-001", "Contrasena incorrecta");

    private final String code;
    private String message;

}