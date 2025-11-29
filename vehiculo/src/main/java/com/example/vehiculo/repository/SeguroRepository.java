package com.example.vehiculo.repository;

import com.example.vehiculo.entity.Seguro;
import com.example.vehiculo.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeguroRepository extends JpaRepository<Seguro,Long> {

    @Override
    Optional<Seguro> findById(Long aLong);
    Optional<Seguro> findByNumeroPoliza(String numeroPoliza);
    boolean existsByNumeroPoliza(String numeroPoliza);
    List<Seguro> findByVehiculo_IdVehiculo(Long idVehiculo);

}
