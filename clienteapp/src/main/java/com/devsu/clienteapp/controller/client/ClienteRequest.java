package com.devsu.clienteapp.controller.client;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class ClienteRequest {

    @NotNull(message = "Nombre es obligatorio")
    @NotBlank(message = "Nombre no puede ser vacio")
    @Pattern(regexp="[a-zA-ZñÑáéíóúÁÉÍÓÚ ]*",
            message = "Nombre no debe contener caracteres especiales")
    @Size(min = 2, message = "Nombre mínimo 2 caracteres")
    @Size(max = 50, message = "Nombre máximo 50 caracteres")
    String nombre;
    @NotNull(message = "Genero es obligatorio")
    @NotBlank(message = "Genero no debe ser vacio")
    @Pattern(regexp = "M|F", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Genero debe ser M o F")
    String genero;
    @NotNull(message = "Edad debe estar entre 18 y 100 años")
    @Min(18)
    @Max(100)
    Short edad;
    @NotNull(message = "Número de documento es obligatorio")
    @NotBlank(message = "Número de documento no puede ser vacio")
    @Pattern(regexp="[\\d]{8}", message = "El número de identificacion debe ser de 8 dígitos")
    String identificacion;
    @Size(min = 10, message = "Nombre mínimo 10 caracteres")
    @Size(max = 50, message = "Nombre máximo 50 caracteres")
    String direccion;
    @Size(min = 9, message = "Telefono debe tener 9 digitos")
    @Size(max = 9, message = "Telefono debe tener 9 digitos")
    String telefono;

}
