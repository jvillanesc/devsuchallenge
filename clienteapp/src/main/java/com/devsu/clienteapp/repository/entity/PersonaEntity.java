package com.devsu.clienteapp.repository.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona")
public class PersonaEntity {

  @Id
  @Column(name = "id_persona")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer idPersona;
  String nombre;
  String genero;
  Short edad;
  String identificacion;
  String direccion;
  String telefono;

}
