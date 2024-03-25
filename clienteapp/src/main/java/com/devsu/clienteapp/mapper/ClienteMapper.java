package com.devsu.clienteapp.mapper;

import com.devsu.clienteapp.controller.client.ClienteRequest;
import com.devsu.clienteapp.controller.client.ClienteResponse;
import com.devsu.clienteapp.controller.client.ClienteUpdateRequest;
import com.devsu.clienteapp.model.Cliente;
import com.devsu.clienteapp.repository.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {

    @Mapping(source = "nombre", target = "persona.nombre")
    @Mapping(source = "genero", target = "persona.genero")
    @Mapping(source = "edad", target = "persona.edad")
    @Mapping(source = "identificacion", target = "persona.identificacion")
    @Mapping(source = "direccion", target = "persona.direccion")
    @Mapping(source = "telefono", target = "persona.telefono")
    ClienteEntity mapClienteEntity(Cliente cliente);


    @Mapping(source = "persona.nombre", target = "nombre")
    @Mapping(source = "persona.genero", target = "genero")
    @Mapping(source = "persona.edad", target = "edad")
    @Mapping(source = "persona.identificacion", target = "identificacion")
    @Mapping(source = "persona.direccion", target = "direccion")
    @Mapping(source = "persona.telefono", target = "telefono")
    Cliente mapCliente(ClienteEntity clienteEntity);

    Cliente mapCliente(ClienteRequest clienteRequest);

    Cliente mapCliente(ClienteUpdateRequest clienteUpdateRequest);

    ClienteResponse mapClienteResponse(Cliente cliente);
}
