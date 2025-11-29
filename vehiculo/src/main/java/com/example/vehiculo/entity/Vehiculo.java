package com.example.vehiculo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//indica que el clase es una entidad
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVehiculo")
    private Long idVehiculo;

    @Column(name = "placa", nullable = false, length = 7)
    private String placa;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "año", nullable = false)
    private Integer anio;

    @Column(name = "color")
    private String color;

    @OneToOne
    @JoinColumn(name = "idConductor", referencedColumnName = "idConductor", nullable = true)
    private Conductor conductor;

}
