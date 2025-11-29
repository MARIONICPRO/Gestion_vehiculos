package com.example.vehiculo.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConductor")
    private Long idConductor;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "licencia", nullable = false, length = 10)
    private String licencia;

    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "direccion", nullable = false, length = 20)
    private String direccion;

    @Column(name = "Activo", nullable = false)
    private Boolean activo;

}

