package com.example.demo.service.Implementacion;

import com.example.demo.entity.Vehiculo;
import com.example.demo.repository.VehiculoRepository;
import com.example.demo.service.VehiculoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 un servicio es una clase que implementa la logica de negocio de la aplicacion
 */
@Service
public class VehiculoServiceImpl implements VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public Vehiculo registerVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }
    public Optional<Vehiculo> searchVehiculoByPlaca(String placa) {
        return vehiculoRepository.findByPlaca(placa);
    }
    @Override
    public List<Vehiculo> listVehiculos() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Optional<Vehiculo> searchVehiculoById(Long idVehiculo) {
        return vehiculoRepository.findByidVehiculo(idVehiculo);
    }

    @SneakyThrows
    @Override
    public Vehiculo updateVehiculo(Long idVehiculo, Vehiculo vehiculo) {
        Vehiculo vehiculoExisting = vehiculoRepository.findByidVehiculo(idVehiculo)
                .orElseThrow(() -> new Exception("Vehiculo with ID " + idVehiculo + " not found"));
        vehiculoExisting.setPlaca(vehiculo.getPlaca());
        vehiculoExisting.setMarca(vehiculo.getMarca());
        vehiculoExisting.setModelo(vehiculo.getModelo());
        vehiculoExisting.setAno(vehiculo.getAno());
        vehiculoExisting.setColor(vehiculo.getColor());

        return vehiculoRepository.save(vehiculoExisting);

    }
    @SneakyThrows
    @Override
    public void deleteVehiculo(Long idVehiculo) {
        Vehiculo vehiculoExisting = vehiculoRepository.findByidVehiculo(idVehiculo)
                .orElseThrow(() -> new Exception("Vehiculo with ID " + idVehiculo + " not found"));

        vehiculoRepository.deleteById(idVehiculo);
    }

}
