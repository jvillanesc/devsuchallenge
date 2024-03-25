package com.devsu.clienteapp.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@JsonRootName("Operacion")
public class OperacionDto implements Serializable {

    String tipoOperacion;
    String identificacion;
}
