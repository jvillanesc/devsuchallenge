package com.devsu.cuentaapp.mapper;

import com.devsu.cuentaapp.controller.client.CuentaRequest;
import com.devsu.cuentaapp.controller.client.CuentaResponse;
import com.devsu.cuentaapp.controller.client.MovimientoRequest;
import com.devsu.cuentaapp.controller.client.MovimientoResponse;
import com.devsu.cuentaapp.model.domain.Cuenta;
import com.devsu.cuentaapp.model.domain.Movimiento;
import com.devsu.cuentaapp.repository.entity.CuentaEntity;
import com.devsu.cuentaapp.repository.entity.MovimientoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovimientoMapper {


    MovimientoEntity mapMovimientoEntity(Movimiento movimiento);
    Movimiento mapMovimiento(MovimientoEntity movimiento);

    @Mapping(target = "monto", expression = "java(asignarSigno(movimiento))")
    MovimientoResponse mapMovimientoResponse(Movimiento movimiento);

    Movimiento mapMovimiento(MovimientoRequest movimiento);

    default Double asignarSigno(Movimiento movimiento) {
        return movimiento.getMonto() * (movimiento.getTipoMovimiento().equals("D")?1:-1);
    }

}
