package com.devsu.clienteapp.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class ClienteEntity {

  @Id
  @Column(name = "id_cliente")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idCliente;
  private String contrasena;
  private Short estado;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
  private PersonaEntity persona;

}
