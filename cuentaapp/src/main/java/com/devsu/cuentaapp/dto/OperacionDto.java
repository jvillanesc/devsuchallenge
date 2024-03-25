package com.devsu.cuentaapp.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@JsonRootName("Operacion")
@AllArgsConstructor
@NoArgsConstructor
public class OperacionDto implements Serializable {

    String tipoOperacion;
    String identificacion;
}
