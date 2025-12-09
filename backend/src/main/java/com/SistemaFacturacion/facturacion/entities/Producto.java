package com.SistemaFacturacion.facturacion.entities;

import com.SistemaFacturacion.facturacion.dtos.CrearProductoDTO;
import com.SistemaFacturacion.facturacion.dtos.UpdateProductoDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "categoria")
    private String categoria;

    @Column(name="precio")
    private BigDecimal precioUnitario;

    @Column(name = "stock")
    private Long stock;

    public Producto(CrearProductoDTO dto){
        this.nombre = dto.nombre();
        this.descripcion = dto.descripcion();
        this.categoria = dto.categoria();
        this.precioUnitario = dto.precioUnitario();
        this.stock = dto.stock();
    }

    public void update(UpdateProductoDTO dto) {
        if (dto.nombre() != null){
            this.nombre = nombre;
        }

        if (dto.descripcion() != null){
            this.descripcion = dto.descripcion();
        }

        if (dto.categoria() != null){
            this.categoria = dto.categoria();
        }

        if (dto.precioUnitario() != null){
            this.precioUnitario = dto.precioUnitario();
        }

        if (dto.stock() != null){
            this.stock = dto.stock();
        }
    }


}
