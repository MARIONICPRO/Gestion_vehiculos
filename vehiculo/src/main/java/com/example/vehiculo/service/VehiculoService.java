package com.example.vehiculo.service;

import com.example.vehiculo.entity.Vehiculo;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface VehiculoService {

    Vehiculo registerVehiculo(Vehiculo vehiculo);//Metodo para registrar un nuevo vehiculo

    List<Vehiculo> listVehiculos();

    Optional<Vehiculo> searchVehiculoById(Long idVehiculo);

    Vehiculo updateVehiculo(Long idVehiculo,Vehiculo vehiculo) throws Exception;

    void deleteVehiculo(Long idVehiculo);//Metodo para eliminar vehicuñp por id

    Optional<Vehiculo> searchVehiculoByPlaca(String placa);

}
