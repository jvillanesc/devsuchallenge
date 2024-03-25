package com.devsu.clienteapp.controller;

import java.time.LocalDateTime;

import com.devsu.clienteapp.controller.client.*;
import com.devsu.clienteapp.mapper.ClienteMapper;
import com.devsu.clienteapp.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;
  @Autowired
  private ClienteMapper clienteMapper;

  @GetMapping("/{identificacion}")
  ResponseEntity<ClienteResponse> obtenerCliente(@PathVariable("identificacion") String identificacion) {
    ClienteResponse clienteResponse =
            clienteMapper.mapClienteResponse(
                    clienteService.obtenerCliente(identificacion));
    return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ClienteResponse> registrarCliente(
          @Valid @RequestBody ClienteRequest clienteRequest){

    ClienteResponse clienteResponse =
            clienteMapper.mapClienteResponse(
                    clienteService.registrarCliente(clienteMapper.mapCliente(clienteRequest)));

    return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
  }

  @DeleteMapping("/{identificacion}")
  public ResponseEntity<Void> eliminarCliente(
          @PathVariable("identificacion")
          @Pattern(regexp="[\\d]{8}", message = "El número de identificacion debe ser de 8 dígitos")
          String identificacion){
    clienteService.eliminarCliente(identificacion);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{identificacion}")
  public ResponseEntity<ClienteResponse> actualizarCliente(
          @PathVariable("identificacion")
          @Pattern(regexp="[\\d]{8}", message = "El número de identificacion debe ser de 8 dígitos")
          String identificacion,
          @Valid @RequestBody ClienteUpdateRequest clienteRequest){

    ClienteResponse clienteResponse =
            clienteMapper.mapClienteResponse(
                    clienteService.actualizarCliente(identificacion, clienteMapper.mapCliente(clienteRequest)));
    return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
  }

  @PatchMapping("/{identificacion}")
  public ResponseEntity<Void> actualizarContrasena(
          @PathVariable("identificacion")
          @Pattern(regexp="[\\d]{8}", message = "El número de identificacion debe ser de 8 dígitos")
          String identificacion,
          @Valid @RequestBody ContrasenaRequest contrasenaRequest){

          clienteService.actualizarContrasena(
                                  identificacion,
                                  contrasenaRequest.getContrasenaActual(),
                                  contrasenaRequest.getContrasenaNueva());

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

    @PostMapping("/{identificacion}/cuentas")
    public ResponseEntity<Void> operarCuentasCliente(
            @PathVariable("identificacion")
            @Pattern(regexp="[\\d]{8}", message = "El número de identificacion debe ser de 8 dígitos")
            String identificacion,
            @Valid @RequestBody OperacionRequest operacionRequest){

        clienteService.operarCuentasCliente(identificacion, operacionRequest.getTipoOperacion(), operacionRequest.getContrasena());

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
