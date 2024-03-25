package com.devsu.cuentaapp.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Table("movimiento")
public class MovimientoEntity {

  @Id
  @Column("id_movimiento")
  private String idMovimiento;
  @Column("fecha")
  private LocalDateTime fecha;
  @Column("tipo_movimiento")
  private String tipoMovimiento;
  @Column("saldo_inicial")
  private Double saldoInicial;
  @Column("monto")
  private Double monto;
  @Column("saldo_disponible")
  private Double saldoDisponible;
  @Column("id_cuenta")
  private Integer idCuenta;

}
