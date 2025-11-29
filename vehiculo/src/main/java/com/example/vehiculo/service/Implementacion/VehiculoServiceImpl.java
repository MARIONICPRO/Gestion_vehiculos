package com.example.vehiculo.service.Implementacion;

import com.example.vehiculo.entity.Conductor;
import com.example.vehiculo.entity.Vehiculo;
import com.example.vehiculo.repository.ConductorRepository;
import com.example.vehiculo.repository.VehiculoRepository;
import com.example.vehiculo.service.VehiculoService;
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

    @Autowired
    private ConductorRepository conductorRepository;

    @Override
    public Vehiculo registerVehiculo(Vehiculo vehiculo) {

        // Si el JSON trae conductor (objeto parcial) -> intentar obtener id
        if (vehiculo.getConductor() != null) {
            Long id = vehiculo.getConductor().getIdConductor();

            if (id == null || id <= 0) {
                throw new RuntimeException("El objeto 'conductor' debe contener un 'idConductor' válido (>0).");
            }

            Conductor conductor = conductorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Conductor no encontrado con ID: " + id));

            // Asignar el conductor real obtenido desde BD
            vehiculo.setConductor(conductor);
        }

        // Validaciones mínimas antes de guardar (ejemplo)
        if (vehiculo.getPlaca() == null || vehiculo.getPlaca().isBlank()) {
            throw new RuntimeException("La placa es obligatoria.");
        }
        if (vehiculo.getAnio() == null) {
            throw new RuntimeException("El año (anio) es obligatorio.");
        }

        return vehiculoRepository.save(vehiculo);
    }

    @Override
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
        vehiculoExisting.setAnio(vehiculo.getAnio());
        vehiculoExisting.setColor(vehiculo.getColor());

        // Si viene conductor en la actualización, validarlo también
        if (vehiculo.getConductor() != null) {
            Long id = vehiculo.getConductor().getIdConductor();
            if (id != null && id > 0) {
                Conductor conductor = conductorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Conductor no encontrado con ID: " + id));
                vehiculoExisting.setConductor(conductor);
            }
        }

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