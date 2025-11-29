package com.example.vehiculo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//indica que el clase es una entidad
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seguro {
    @Id //indica que este campo es la clave priamria
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Especifica que el valor del id se genera autmaticamente
    @Column(name = "idSeguro")
    private Long idSeguro;

    @ManyToOne
    @JoinColumn(name ="idVehiculo" , referencedColumnName = "idVehiculo")//mapea este campo a una columna de la tabla se pueden agregar restricciones
    private Vehiculo vehiculo;

    @Column(name = "compañia" , nullable = false)
    private String compania;

    @Column(name = "numeroPoliza",nullable = false)
    private String numeroPoliza;

    @Column(name = "fechaInicio",nullable = false,length = 10)
    private String fechaInicio;

    @Column(name = "fechaVencimiento",nullable = false,length = 10 )
    private  String fechaVencimiento;
}
