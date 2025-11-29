package com.example.vehiculo.service;

import com.example.vehiculo.entity.Seguro;

import java.util.List;
import java.util.Optional;

public interface SeguroService {
    Seguro registerSeguro(Seguro seguro);

    List<Seguro> listSeguros();

    Optional<Seguro> searchSeguroById(Long idSeguro);

    Optional<Seguro> searchSeguroByPoliza(String numeroPoliza);

    Seguro updateSeguro(Long idSeguro, Seguro seguro) throws Exception;

    void deleteSeguro(Long idSeguro);

    List<Seguro> listSegurosByVehiculo(Long idVehiculo);

}
