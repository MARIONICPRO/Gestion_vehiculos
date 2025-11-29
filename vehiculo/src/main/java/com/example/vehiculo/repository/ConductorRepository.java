package com.example.vehiculo.repository;

import com.example.vehiculo.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConductorRepository extends JpaRepository<Conductor,Long> {

    Boolean existsByNombre(String nombre);

    Optional<Conductor> findByNombre(String nombre);

    Optional<Conductor> findByLicencia(String licencia);
}

