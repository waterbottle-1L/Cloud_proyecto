package com.SistemaFacturacion.facturacion.dtos.producto;

import java.math.BigDecimal;

public record CrearProductoDTO(String nombre, String descripcion,
                               String categoria, BigDecimal precioUnitario, Long stock) {
}
