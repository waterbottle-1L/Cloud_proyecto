package com.SistemaFacturacion.facturacion.dtos.comprobante;

import java.math.BigDecimal;

public record ItemComprobanteDTO(
        Long productoId,
        Integer cantidad,
        BigDecimal precioUnitario) {
}
