package com.devsu.clienteapp.service;


import com.devsu.clienteapp.dao.ClienteDao;
import com.devsu.clienteapp.dto.OperacionDto;
import com.devsu.clienteapp.error.BussinessException;
import com.devsu.clienteapp.error.enums.BussinesErrorEnum;
import com.devsu.clienteapp.event.KafkaProducer;
import com.devsu.clienteapp.model.Cliente;
import com.devsu.clienteapp.util.Constant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ClienteService {

  ClienteDao clienteDao;

  KafkaProducer kafkaProducer;

  public Cliente registrarCliente(Cliente cliente) {
    log.info("Registrar cliente {}", cliente.getIdentificacion());
    cliente.setEstado(Constant.CLIENTE_ESTADO_ACTIVO);
    cliente.setContrasena(cliente.getIdentificacion());
    return clienteDao.registrarCliente(cliente);
  }

  public Cliente obtenerCliente(String identificacion) {
    log.info("Obtención cliente {}", identificacion);
    return clienteDao.obtenerCliente(identificacion);
  }

  public void eliminarCliente(String identificacion) {
      log.info("Eliminar cliente {}", identificacion);
      clienteDao.eliminarCliente(identificacion);
  }

  public Cliente actualizarCliente(String identificacion, Cliente cliente) {
    log.info("Actualizar cliente {}", identificacion);
    return clienteDao.actualizarCliente(identificacion, cliente);
  }

  public void actualizarContrasena(String identificacion, String contrasenaActual, String nuevaContrasena) {
    log.info("Actualizar contrasena {}", identificacion);
    Cliente cliente = clienteDao.obtenerCliente(identificacion);
    if (!cliente.getContrasena().equals(contrasenaActual))
      throw new BussinessException(BussinesErrorEnum.CONSTRASENA_INCORRECTA);
    clienteDao.actualizarContrasena(identificacion, nuevaContrasena);
  }

  public void operarCuentasCliente(String identificacion, String tipoOperacion, String contrasena) {
    log.info("Operar cuentas cliente {} {}", identificacion, tipoOperacion);
    Cliente cliente = clienteDao.obtenerCliente(identificacion);
    if (!cliente.getContrasena().equals(contrasena))
      throw new BussinessException(BussinesErrorEnum.CONSTRASENA_INCORRECTA);
    kafkaProducer.sendFlightEvent(OperacionDto.builder()
            .identificacion(identificacion)
            .tipoOperacion(tipoOperacion)
            .build());
  }
}
