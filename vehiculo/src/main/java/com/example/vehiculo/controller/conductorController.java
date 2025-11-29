package com.example.vehiculo.controller;

import com.example.vehiculo.entity.Conductor;
import com.example.vehiculo.entity.Seguro;
import com.example.vehiculo.entity.Vehiculo;
import com.example.vehiculo.service.ConductorService;
import com.example.vehiculo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conductor")
public class conductorController {

    @Autowired
    private ConductorService conductorService;

    // REGISTRAR CONDUCTOR
    @PostMapping("/register")
    public ResponseEntity<?> registerConductor(@RequestBody Conductor conductor) {

        // Validar campos obligatorios
        if (conductor.getNombre() == null || conductor.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre es obligatorio.");
        }
        if (conductor.getLicencia() == null || conductor.getLicencia().isEmpty()) {
            return ResponseEntity.badRequest().body("La licencia es obligatoria.");
        }
        if (conductor.getTelefono() == null || conductor.getTelefono().isEmpty()) {
            return ResponseEntity.badRequest().body("El teléfono es obligatorio.");
        }
        if (conductor.getDireccion() == null || conductor.getDireccion().isEmpty()) {
            return ResponseEntity.badRequest().body("La dirección es obligatoria.");
        }

        /*profe no puse comprobante de que existe como
        no hemos hecho eso de que una persona puede tener varios autos
        entonces deje que se pudieran agregar cuantas personas quieran por el mismo numero
        ya que tampoco pidio documento en el conductor Entity =)
         */

        // Guardar
        Conductor nuevo = conductorService.registerConductor(conductor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // LISTAR TODOS
    @GetMapping("/list")
    public ResponseEntity<List<Conductor>> listConductores() {
        return ResponseEntity.ok(conductorService.listConductores());
    }

    // BUSCAR POR ID
    @GetMapping("/search/id/{idConductor}")
    public ResponseEntity<?> searchConductorById(@PathVariable Long idConductor) {
        Optional<Conductor> conductor = conductorService.searchConductorById(idConductor);

        return conductor.isPresent()
                ? ResponseEntity.ok(conductor.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No existe conductor con ID " + idConductor);
    }

    // ACTUALIZAR
    @PutMapping("/update/{idConductor}")
    public ResponseEntity<?> updateConductor(
            @PathVariable Long idConductor,
            @RequestBody Conductor conductor) {

        try {
            Conductor updated = conductorService.updateConductor(idConductor, conductor);
            return ResponseEntity.ok(updated);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    // ELIMINAR
    @DeleteMapping("/delete/{idConductor}")
    public ResponseEntity<?> deleteConductor(@PathVariable Long idConductor) {
        try {
            conductorService.deleteConductor(idConductor);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
