package com.example.vehiculo.repository;

import com.example.vehiculo.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo,Long> {
    /*
    No veo necesario otros metodos de busqueda ya que este
    es el mas especifico aparte de la placa
    */
    //MetodoID
    boolean existsByPlaca(String placa);
    Optional<Vehiculo> findByidVehiculo(Long idVehiculo);
    Optional<Vehiculo> findByPlaca(String placa);

}
