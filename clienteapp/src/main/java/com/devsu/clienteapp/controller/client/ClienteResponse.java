package com.devsu.clienteapp.controller.client;

import lombok.Data;

@Data
public class ClienteResponse {

    String nombre;
    String genero;
    Short edad;
    String identificacion;
    String direccion;
    String telefono;
    Short estado;

}
