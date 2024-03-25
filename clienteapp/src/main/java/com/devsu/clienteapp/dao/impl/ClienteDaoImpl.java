package com.devsu.clienteapp.dao.impl;

import com.devsu.clienteapp.dao.ClienteDao;
import com.devsu.clienteapp.model.Cliente;
import com.devsu.clienteapp.error.BussinessException;
import com.devsu.clienteapp.error.enums.BussinesErrorEnum;
import com.devsu.clienteapp.mapper.ClienteMapper;
import com.devsu.clienteapp.repository.ClienteRepository;
import com.devsu.clienteapp.repository.entity.ClienteEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ClienteDaoImpl implements ClienteDao {

    ClienteRepository clienteRepository;
    ClienteMapper clienteMapper;

    public Cliente registrarCliente(Cliente cliente){
        if(clienteRepository.findByPersonaIdentificacion(cliente.getIdentificacion()).isPresent()){
            throw new BussinessException(BussinesErrorEnum.CLIENTE_EXISTE);
        }
        return clienteMapper.mapCliente(
                clienteRepository.save(clienteMapper.mapClienteEntity(cliente)));
    }

    @Override
    public Cliente obtenerCliente(String identificacion) {
        return clienteMapper.mapCliente(
                clienteRepository.findByPersonaIdentificacion(identificacion)
                        .orElseThrow(() -> {
                            log.error("Error:{} : {}", BussinesErrorEnum.CLIENTE_NO_EXISTE.getMessage(), identificacion);
                            return new BussinessException(BussinesErrorEnum.CLIENTE_NO_EXISTE);
                        }));
    }

    @Override
    public void eliminarCliente(String identificacion) {
        ClienteEntity clienteEntity = clienteRepository.findByPersonaIdentificacion(identificacion)
                        .orElseThrow(() -> new BussinessException(BussinesErrorEnum.CLIENTE_NO_EXISTE));
        clienteRepository.delete(clienteEntity);
    }

    @Override
    public Cliente actualizarCliente(String identificacion, Cliente cliente) {
        ClienteEntity clienteEntity = clienteRepository.findByPersonaIdentificacion(identificacion)
                        .orElseThrow(() -> new BussinessException(BussinesErrorEnum.CLIENTE_NO_EXISTE));
        clienteEntity.getPersona().setDireccion(cliente.getDireccion());
        clienteEntity.getPersona().setGenero(cliente.getGenero());
        clienteEntity.getPersona().setTelefono(cliente.getTelefono());
        return clienteMapper.mapCliente(
                clienteRepository.save(clienteEntity));
    }

    @Override
    public void actualizarContrasena(String identificacion, String nuevaContrasena) {
        ClienteEntity clienteEntity = clienteRepository.findByPersonaIdentificacion(identificacion)
                .orElseThrow(() -> new BussinessException(BussinesErrorEnum.CLIENTE_NO_EXISTE));
        clienteEntity.setContrasena(nuevaContrasena);
        clienteRepository.save(clienteEntity);
    }

}
