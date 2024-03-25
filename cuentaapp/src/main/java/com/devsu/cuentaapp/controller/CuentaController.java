package com.devsu.cuentaapp.controller;

import com.devsu.cuentaapp.controller.client.CuentaRequest;
import com.devsu.cuentaapp.controller.client.CuentaResponse;
import com.devsu.cuentaapp.controller.client.MovimientoRequest;
import com.devsu.cuentaapp.controller.client.MovimientoResponse;
import com.devsu.cuentaapp.mapper.CuentaMapper;
import com.devsu.cuentaapp.mapper.MovimientoMapper;
import com.devsu.cuentaapp.service.CuentaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CuentaController {

  CuentaService cuentaService;
  CuentaMapper cuentaMapper;
  MovimientoMapper movimientoMapper;

  @GetMapping("/cuentas")
  @ResponseStatus(HttpStatus.OK)
  public Flux<CuentaResponse> obtenerCuentasActivas() {
    return cuentaService.obtenerCuentasActivas()
            .map(cuentaMapper::mapCuentaResponse);
  }

  @GetMapping("/cuentas/{nroCuenta}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<CuentaResponse> obtenerCuenta(
          @PathVariable("nroCuenta")
          @Pattern(regexp="[\\d]{6}", message = "El número de cuenta debe ser de 6 dígitos")
          String nroCuenta) {
    return cuentaService.obtenerCuenta(nroCuenta)
            .map(cuentaMapper::mapCuentaResponse);
  }

  @PostMapping("/cuentas")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<CuentaResponse> crearCuenta(@Valid @RequestBody CuentaRequest cuentaRequest) {
    return cuentaService.crearCuenta(cuentaMapper.mapCuenta(cuentaRequest))
            .map(cuentaMapper::mapCuentaResponse);
  }

  @PostMapping("/cuentas/{nroCuenta}/movimientos")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<MovimientoResponse> crearMovimiento(
          @PathVariable("nroCuenta")
          @Pattern(regexp="[\\d]{6}", message = "El número de cuenta debe ser de 6 dígitos")
          String nroCuenta,
          @Valid @RequestBody MovimientoRequest movimientoRequest) {
    return cuentaService.registrarMovimiento(
                    nroCuenta,
                    movimientoMapper.mapMovimiento(movimientoRequest))
            .map(movimientoMapper::mapMovimientoResponse);
  }

  @GetMapping("/cuentas/{nroCuenta}/movimientos")
  @ResponseStatus(HttpStatus.OK)
  public Flux<MovimientoResponse> obtenerMovimientosPorCuenta(
          @PathVariable("nroCuenta")
          @Pattern(regexp="[\\d]{6}", message = "El número de cuenta debe ser de 6 dígitos")
          String nroCuenta) {
    return cuentaService.obtenerMovimientos(nroCuenta)
            .map(movimientoMapper::mapMovimientoResponse);
  }

  @GetMapping("/movimientos")
  @ResponseStatus(HttpStatus.OK)
  public Flux<MovimientoResponse> obtenerMovimientos(
          @RequestParam("cliente")
          @NotNull(message = "Número de documento es obligatorio")
          @NotBlank(message = "Número de documento no puede ser vacio")
          @Pattern(regexp="[\\d]{8}", message = "El número de identificacion debe ser de 8 dígitos")
          String cliente,
          @RequestParam("fecha")
          //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate fecha) {
    return cuentaService.obtenerMovimientos(cliente, fecha)
            .map(movimientoMapper::mapMovimientoResponse);
  }

}
