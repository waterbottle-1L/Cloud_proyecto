package com.SistemaFacturacion.facturacion.dtos.cliente;

import com.SistemaFacturacion.facturacion.entities.TipoDocumento;

public record UpdateClienteDTO(String nombres, String apellidos, TipoDocumento tipoDocumento,
                               String numeroDocumento, String direccion) {
}
