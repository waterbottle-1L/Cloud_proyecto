package com.SistemaFacturacion.facturacion.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cajeros")
public class Cajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres")
    private String nombre;

    @Column(name = "dni")
    private String dni;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
