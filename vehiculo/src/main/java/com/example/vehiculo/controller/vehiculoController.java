package com.example.vehiculo.controller;

import com.example.vehiculo.entity.Vehiculo;
import com.example.vehiculo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vehiculo")
public class vehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registerProduct(@RequestBody Vehiculo vehiculo){
        Optional<Vehiculo> existente = vehiculoService.searchVehiculoByPlaca(vehiculo.getPlaca());

        if (existente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ya existe un vehículo con esta placa: " + vehiculo.getPlaca());
        }

        Vehiculo newVehiculo = vehiculoService.registerVehiculo(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVehiculo);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Vehiculo>>listVehiculo(){
        List<Vehiculo> vehiculos = vehiculoService.listVehiculos();
        return ResponseEntity.ok(vehiculos);
    }
    @GetMapping("/search/id/{idVehiculo}")
    public ResponseEntity<?> searchVehiculoById(@PathVariable Long idVehiculo) {
        Optional<Vehiculo> vehiculo = vehiculoService.searchVehiculoById(idVehiculo);

        return vehiculo.isPresent()
                ? ResponseEntity.ok(vehiculo.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehiculo no encontrado");
    }

    @PutMapping("/update/{idVehiculo}")
    public ResponseEntity<?> updateProduct(@PathVariable Long idVehiculo,@RequestBody Vehiculo vehiculo){
      try{
          Vehiculo updatevehiculo = new Vehiculo();
          updatevehiculo.setPlaca(vehiculo.getPlaca());
          updatevehiculo.setMarca(vehiculo.getMarca());
          updatevehiculo.setModelo(vehiculo.getModelo());
          updatevehiculo.setAnio(vehiculo.getAnio());
          updatevehiculo.setColor(vehiculo.getColor());

          Vehiculo vehiculoDB = vehiculoService.updateVehiculo(idVehiculo,updatevehiculo);
          return  ResponseEntity.ok(vehiculoDB);
      } catch (Exception exception) {
          System.out.println(exception.getMessage());
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
      }
    }
    @DeleteMapping("/delete/{idVehiculo}")
    public ResponseEntity<?> deleteProduct(@PathVariable long idVehiculo){
        try{
            vehiculoService.deleteVehiculo(idVehiculo);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
    @GetMapping("/search/placa/{placa}")
    public ResponseEntity<?> searchVehiculoByPlaca(@PathVariable String placa) {

        Optional<Vehiculo> vehiculo = vehiculoService.searchVehiculoByPlaca(placa);

        return vehiculo.isPresent()
                ? ResponseEntity.ok(vehiculo.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehículo no encontrado con placa: " + placa);
    }

}
