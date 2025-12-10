package com.SistemaFacturacion.facturacion.dtos;

import com.SistemaFacturacion.facturacion.entities.Rol;

public record CrearUsuarioDTO(String dni, String nombres, String apellidos,
                              String username, String password, Rol rol) {
}
