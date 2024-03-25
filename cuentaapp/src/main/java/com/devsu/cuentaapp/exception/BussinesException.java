package com.devsu.cuentaapp.exception;

import com.devsu.cuentaapp.exception.enums.BussinesErrorEnum;

public class BussinesException extends HiperiumException {

    public BussinesException(BussinesErrorEnum errorEnum) {
        super(errorEnum.getCode(), errorEnum.getMessage());
    }
}
