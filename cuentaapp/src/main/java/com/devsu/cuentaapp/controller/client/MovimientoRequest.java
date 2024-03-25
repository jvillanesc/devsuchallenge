package com.devsu.cuentaapp.controller.client;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoRequest {

  @NotNull(message = "El saldo inicial es obligatorio")
  @DecimalMin(value = "0.01", message = "El monto de la operacion no debe ser mayor a 3000 S/.")
  @DecimalMax(value = "3000", message = "El monto de la operacion no debe ser mayor a 3000 S/.")
  private Double monto;

  @NotNull(message = "El tipo de movimiento es obligatorio")
  @NotBlank(message = "El tipo de movimiento no debe ser vacio")
  @Pattern(regexp = "R|D", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El tipo de movimiento debe ser R(retiro) o D(deposito)")
  String tipoMovimiento;

}
