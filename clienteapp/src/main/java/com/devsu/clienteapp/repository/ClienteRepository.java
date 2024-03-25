package com.devsu.clienteapp.repository;



import com.devsu.clienteapp.repository.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    Optional<ClienteEntity> findByPersonaIdentificacion(String identificacion);

}
