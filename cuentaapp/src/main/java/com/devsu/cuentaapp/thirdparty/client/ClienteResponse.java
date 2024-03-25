package com.devsu.cuentaapp.thirdparty.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteResponse {

    String nombre;
    String genero;
    Short edad;
    String identificacion;
    String direccion;
    String telefono;
    Short estado;

}
