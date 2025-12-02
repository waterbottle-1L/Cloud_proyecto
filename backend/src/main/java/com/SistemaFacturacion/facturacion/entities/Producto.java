package com.SistemaFacturacion.facturacion.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres")
    private String nombreProducto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name="precio")
    private BigDecimal precioUnitario;

}
