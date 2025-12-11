package com.SistemaFacturacion.facturacion.dtos.producto;

import com.SistemaFacturacion.facturacion.entities.Producto;

import java.math.BigDecimal;

public record ProductoSummaryDTO (Long id, String nombre, String descripcion, String categoria,
                                  BigDecimal precioUnitario, Long stock){
    public ProductoSummaryDTO(Producto p){
        this(p.getId(), p.getNombre(), p.getDescripcion(),
                p.getCategoria(),p.getPrecioUnitario(), p.getStock());
    }
}
