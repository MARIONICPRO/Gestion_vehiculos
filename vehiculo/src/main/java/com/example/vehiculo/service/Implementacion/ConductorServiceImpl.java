package com.example.vehiculo.service.Implementacion;

import com.example.vehiculo.entity.Conductor;
import com.example.vehiculo.entity.Vehiculo;
import com.example.vehiculo.repository.ConductorRepository;
import com.example.vehiculo.service.ConductorService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConductorServiceImpl implements ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    @Override
    public Conductor registerConductor(Conductor conductor) {
        return conductorRepository.save(conductor);
    }
    @Override
    public List<Conductor> listConductores() {
        return conductorRepository.findAll();
    }

    @Override
    public Optional<Conductor> searchConductorById(Long idConductor) {
        return conductorRepository.findById(idConductor);
    }

    @SneakyThrows
    @Override
    public Conductor updateConductor(Long idConductor, Conductor conductor) throws Exception {
        Conductor conductorExisting = conductorRepository.findById(idConductor)
                .orElseThrow(() -> new Exception("Conductor with ID " + idConductor + " not found"));

        conductorExisting.setNombre(conductor.getNombre());
        conductorExisting.setLicencia(conductor.getLicencia());
        conductorExisting.setTelefono(conductor.getTelefono());

        // Solo actualizar 'activo' si viene en el request
        if (conductor.getActivo() != null) {
            conductorExisting.setActivo(conductor.getActivo());
        }

        return conductorRepository.save(conductorExisting);
    }


    @SneakyThrows
    @Override
    public void deleteConductor(Long idConductor) {
        Conductor conductorExisting = conductorRepository.findById(idConductor)
                .orElseThrow(() -> new Exception("Vehiculo with ID " + idConductor + " not found"));

        conductorRepository.deleteById(idConductor);
    }

    @Override
    public Optional<Conductor> searchConductorByName(String nombre) {
        return conductorRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Conductor> findByLicencia(String licencia) {
        return conductorRepository.findByLicencia(licencia);
    }
}
