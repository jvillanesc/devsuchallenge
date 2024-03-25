package com.devsu.cuentaapp.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("cuenta")
public class CuentaEntity {

  @Id
  @Column("id_cuenta")
  private Integer idCuenta;
  @Column("nro_cuenta")
  private String nroCuenta;
  @Column("tipo_cuenta")
  private String tipoCuenta;
  @Column("saldo_inicial")
  private Double saldoInicial;
  @Column("identificacion_cliente")
  private String identificacionCliente;
  @Column("nombre_cliente")
  private String nombreCliente;
  @Column("estado")
  private Short estado;

}
