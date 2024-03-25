package com.devsu.cuentaapp.controller.client;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaRequest {

  @NotNull(message = "El número de cuenta es obligatorio")
  @NotBlank(message = "El número de cuenta no puede ser vacio")
  @Pattern(regexp="[\\d]{6}", message = "El número de cuenta debe tener 6 digitos")
  private String nroCuenta;

  @NotNull(message = "El tipo de cuenta es obligatorio")
  @NotBlank(message = "El tipo de cuenta no puede ser vacio")
  @Pattern(regexp = "C|A", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El tipo de cuenta debe ser C o A")
  private String tipoCuenta;

  @NotNull(message = "El saldo inicial es obligatorio")
  @Min(value = 0, message = "La saldo inicial ser mayor o igual a 0.00")
  private Double saldoInicial;

  @NotNull(message = "Número de documento es obligatorio")
  @NotBlank(message = "Número de documento no puede ser vacio")
  @Pattern(regexp="[\\d]{8}", message = "El número de identificacion debe ser de 8 dígitos")
  private String identificacionCliente;

}
