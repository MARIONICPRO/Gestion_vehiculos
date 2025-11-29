package com.example.vehiculo.service;

import com.example.vehiculo.entity.Conductor;
import com.example.vehiculo.entity.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface ConductorService {

    Conductor registerConductor(Conductor conductor);//Metodo para registrar un nuevo vehiculo

    List<Conductor> listConductores();

    Optional<Conductor> searchConductorById(Long idConductor);

    Conductor updateConductor(Long idConductor,Conductor conductor) throws Exception;

    void deleteConductor(Long idConductor);//Metodo para eliminar vehicuñp por id

    Optional<Conductor> searchConductorByName(String nombre);

    Optional<Conductor> findByLicencia(String licencia);

}
