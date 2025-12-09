package com.SistemaFacturacion.facturacion.dtos;

import com.SistemaFacturacion.facturacion.entities.Producto;

import java.math.BigDecimal;

public record ProductoResponseDTO(Long id, String nombre, String descripcion, String categoria,
                                  BigDecimal precioUnitario, Long stock) {
    public ProductoResponseDTO(Producto p){
        this(p.getId(), p.getNombre(), p.getDescripcion(),
                p.getCategoria(),p.getPrecioUnitario(), p.getStock());
    }
}
