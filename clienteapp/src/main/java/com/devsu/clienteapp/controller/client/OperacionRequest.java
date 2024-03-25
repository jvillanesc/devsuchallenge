package com.devsu.clienteapp.controller.client;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class OperacionRequest {
    @NotNull(message = "Genero es obligatorio")
    @NotBlank(message = "Genero no debe ser vacio")
    @Pattern(regexp = "B|A", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Genero debe ser B(bloqueo) o A(activacion)")
    String tipoOperacion;
    @Size(min = 4, message = "Contraseña minimo 4 caracteres")
    @Size(max = 16, message = "Contraseña minimo 16 caracteres")
    String contrasena;
}
