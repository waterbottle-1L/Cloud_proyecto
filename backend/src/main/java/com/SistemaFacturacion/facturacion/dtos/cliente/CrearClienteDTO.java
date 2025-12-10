package com.SistemaFacturacion.facturacion.dtos;

import com.SistemaFacturacion.facturacion.entities.TipoDocumento;

public record CrearClienteDTO(String nombres,String apellidos, TipoDocumento tipoDocumento,
                              String numeroDocumento, String direccion) {
}
