package com.example.vehiculo.controller;

import com.example.vehiculo.entity.Conductor;
import com.example.vehiculo.entity.Seguro;
import com.example.vehiculo.entity.Vehiculo;
import com.example.vehiculo.service.ConductorService;
import com.example.vehiculo.service.SeguroService;
import com.example.vehiculo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seguro")
public class seguroController {

    @Autowired
    private SeguroService seguroService;

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/register")
    public ResponseEntity<?> registerSeguro(@RequestBody Seguro seguro) {

        Optional<Seguro> existente = seguroService.searchSeguroByPoliza(seguro.getNumeroPoliza());

        if (existente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ya existe un seguro  con esta poliza: " + seguro.getNumeroPoliza());
        }

        // Validar que venga el vehículo
        if (seguro.getVehiculo() == null || seguro.getVehiculo().getIdVehiculo() == 0) {
            return ResponseEntity.badRequest().body("Debe enviar un vehículo válido con ID.");
        }

        // Buscar el vehículo real en la base de datos
        Vehiculo vehiculo = vehiculoService.searchVehiculoById(seguro.getVehiculo().getIdVehiculo())
                .orElse(null);

        if (vehiculo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vehículo no encontrado con ID: " + seguro.getVehiculo().getIdVehiculo());
        }

        // Asignar el vehículo encontrado al seguro
        seguro.setVehiculo(vehiculo);

        // Guardar seguro
        Seguro newSeguro = seguroService.registerSeguro(seguro);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSeguro);
    }




    @GetMapping("/list")
    public ResponseEntity<List<Seguro>> listSeguros() {
        return ResponseEntity.ok(seguroService.listSeguros());
    }

    @GetMapping("/search/id/{idSeguro}")
    public ResponseEntity<?> searchSeguroById(@PathVariable Long idSeguro) {

        Optional<Seguro> seguro = seguroService.searchSeguroById(idSeguro);

        return seguro.isPresent()
                ? ResponseEntity.ok(seguro.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Seguro no encontrado con ID: " + idSeguro);
    }

    @GetMapping("/search/poliza/{numeroPoliza}")
    public ResponseEntity<?> searchSeguroByPoliza(@PathVariable String numeroPoliza) {

        Optional<Seguro> seguro = seguroService.searchSeguroByPoliza(numeroPoliza);

        return seguro.isPresent()
                ? ResponseEntity.ok(seguro.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Seguro no encontrado con póliza: " + numeroPoliza);
    }

    @PutMapping("/update/{idSeguro}")
    public ResponseEntity<?> updateSeguro(@PathVariable Long idSeguro,
                                          @RequestBody Seguro seguro) {
        try {
            Seguro updated = seguroService.updateSeguro(idSeguro, seguro);
            return ResponseEntity.ok(updated);

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{idSeguro}")
    public ResponseEntity<?> deleteSeguro(@PathVariable Long idSeguro) {
        try {
            seguroService.deleteSeguro(idSeguro);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/list/vehiculo/{idVehiculo}")
    public ResponseEntity<?> listSegurosPorVehiculo(@PathVariable Long idVehiculo) {

        List<Seguro> seguros = seguroService.listSegurosByVehiculo(idVehiculo);

        if (seguroService.listSegurosByVehiculo(idVehiculo).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay seguros registrados para el vehículo con ID: " + idVehiculo);
        }

        return ResponseEntity.ok(seguros);
    }

}

