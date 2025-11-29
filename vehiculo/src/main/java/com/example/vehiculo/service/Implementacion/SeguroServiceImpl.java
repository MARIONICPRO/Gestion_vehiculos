package com.example.vehiculo.service.Implementacion;

import com.example.vehiculo.entity.Seguro;
import com.example.vehiculo.repository.SeguroRepository;
import com.example.vehiculo.service.SeguroService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeguroServiceImpl implements SeguroService {
    @Autowired
    private SeguroRepository seguroRepository;

    @Override
    public Seguro registerSeguro(Seguro seguro) {
        return seguroRepository.save(seguro);
    }

    @Override
    public List<Seguro> listSeguros() {
        return seguroRepository.findAll();
    }

    @Override
    public Optional<Seguro> searchSeguroById(Long idSeguro) {
        return seguroRepository.findById(idSeguro);
    }

    @Override
    public Optional<Seguro> searchSeguroByPoliza(String numeroPoliza) {
        return seguroRepository.findByNumeroPoliza(numeroPoliza);
    }

    @SneakyThrows
    @Override
    public Seguro updateSeguro(Long idSeguro, Seguro seguro) throws Exception {
        Seguro existingSeguro = seguroRepository.findById(idSeguro)
                .orElseThrow(() -> new Exception("Seguro con ID " + idSeguro + " no encontrado"));

        existingSeguro.setVehiculo(seguro.getVehiculo());
        existingSeguro.setCompania(seguro.getCompania());
        existingSeguro.setNumeroPoliza(seguro.getNumeroPoliza());
        existingSeguro.setFechaInicio(seguro.getFechaInicio());
        existingSeguro.setFechaVencimiento(seguro.getFechaVencimiento());

        return seguroRepository.save(existingSeguro);
    }

    @Override
    public void deleteSeguro(Long idSeguro) {
        seguroRepository.deleteById(idSeguro);
    }

    @Override
    public List<Seguro> listSegurosByVehiculo(Long idVehiculo) {
        return seguroRepository.findByVehiculo_IdVehiculo(idVehiculo);
    }

}
