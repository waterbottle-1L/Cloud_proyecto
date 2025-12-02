package com.SistemaFacturacion.facturacion.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres")
    private String nombre;

    @Column(name = "documento")
    private TipoDocumento tipoDocumento;

    @Column(name="numero")
    private String numeroDocumento;

    @Column(name="direccion")
    private String direccion;

}
