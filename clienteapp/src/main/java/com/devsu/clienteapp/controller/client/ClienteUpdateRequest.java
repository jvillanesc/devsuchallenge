package com.devsu.clienteapp.controller.client;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class ClienteUpdateRequest {

    @NotNull(message = "Genero es obligatorio")
    @NotBlank(message = "Genero no debe ser vacio")
    @Pattern(regexp = "M|F", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Genero debe ser M o F")
    String genero;
    @Size(min = 10, message = "Nombre mínimo 10 caracteres")
    @Size(max = 50, message = "Nombre máximo 50 caracteres")
    String direccion;
    @Size(min = 9, message = "Telefono debe tener 9 digitos")
    @Size(max = 9, message = "Telefono debe tener 9 digitos")
    String telefono;

}
