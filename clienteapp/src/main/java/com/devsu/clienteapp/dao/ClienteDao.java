package com.devsu.clienteapp.dao;


import com.devsu.clienteapp.model.Cliente;

public interface ClienteDao {

    Cliente registrarCliente(Cliente cliente);

    Cliente obtenerCliente(String identificacion);

    void eliminarCliente(String identificacion);

    Cliente actualizarCliente(String identificacion, Cliente cliente);

    void actualizarContrasena(String identificacion, String nuevaContrasena);
}
