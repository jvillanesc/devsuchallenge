package com.devsu.cuentaapp.controller.client;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoResponse {

  @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
  private LocalDateTime fecha;
  private String saldoInicial;
  private Double monto;
  private String saldoDisponible;


}
