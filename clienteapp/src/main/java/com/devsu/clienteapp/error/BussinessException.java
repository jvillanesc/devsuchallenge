package com.devsu.clienteapp.error;


import com.devsu.clienteapp.error.enums.BussinesErrorEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BussinessException extends HiperiumException{
    public BussinessException(BussinesErrorEnum errorEnum) {
        super(errorEnum.getCode(), errorEnum.getMessage());
    }
}