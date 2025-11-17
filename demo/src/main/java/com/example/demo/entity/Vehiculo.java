package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//indica que el clase es una entidad
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehiculo {
    @Id //indica que este campo es la clave priamria
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Especifica que el valor del id se genera autmaticamente
    @Column(name = "idVehiculo")
    private long idVehiculo;

    @Column(name = "placa",nullable = false,length = 7)//mapea este campo a una columna de la tabla se pueden agregar restricciones
    private String placa;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "año",nullable = false)
    private Integer ano;

    @Column(name = "color")
    private String color;

}
