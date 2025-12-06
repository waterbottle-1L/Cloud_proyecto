package com.SistemaFacturacion.facturacion.dtos;

import com.SistemaFacturacion.facturacion.entities.Cliente;
import com.SistemaFacturacion.facturacion.entities.TipoDocumento;

import java.time.LocalDateTime;

public record ClienteSummaryDTO(Long id, String nombres, String apellidos, TipoDocumento tipoDocumento,
                                String numeroDocumento, String direccion, LocalDateTime createdAt) {
    public ClienteSummaryDTO(Cliente cl) {
        this(cl.getId(), cl.getNombres(),cl.getApellidos(), cl.getTipoDocumento(),
                cl.getNumeroDocumento(), cl.getDireccion(), cl.getCreatedAt());
    }
}
