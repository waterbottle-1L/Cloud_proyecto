package com.SistemaFacturacion.facturacion.dtos.usuario;

import com.SistemaFacturacion.facturacion.entities.Rol;

public record UpdateUsuarioDTO(String dni, String nombres, String apellidos,
                               String username, String password, Rol rol) {
}
