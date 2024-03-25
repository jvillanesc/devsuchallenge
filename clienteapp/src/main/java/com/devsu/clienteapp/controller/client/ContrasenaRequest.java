package com.devsu.clienteapp.controller.client;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ContrasenaRequest {

    @Size(min = 4, message = "Contraseña minimo 4 caracteres")
    @Size(max = 16, message = "Contraseña minimo 16 caracteres")
    String contrasenaActual;
    @Size(min = 4, message = "Contraseña minimo 4 caracteres")
    @Size(max = 16, message = "Contraseña minimo 16 caracteres")
    String contrasenaNueva;

}
