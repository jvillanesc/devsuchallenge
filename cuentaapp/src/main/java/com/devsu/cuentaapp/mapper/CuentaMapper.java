package com.devsu.cuentaapp.mapper;

import com.devsu.cuentaapp.model.domain.Cuenta;
import com.devsu.cuentaapp.repository.entity.CuentaEntity;
import com.devsu.cuentaapp.controller.client.CuentaRequest;
import com.devsu.cuentaapp.controller.client.CuentaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CuentaMapper {

    Cuenta mapCuenta(CuentaRequest cuentaRequest);

    CuentaEntity mapCuentaEntity(Cuenta cuenta);

    Cuenta mapCuenta(CuentaEntity cuentaEntity);

    CuentaResponse mapCuentaResponse(Cuenta cuenta);

}
