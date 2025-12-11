package com.SistemaFacturacion.facturacion.dtos.usuario;

import com.SistemaFacturacion.facturacion.entities.Rol;
import com.SistemaFacturacion.facturacion.entities.Usuario;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(Long id, String dni, String nombres, String apellidos,
                                 String username, String password, Rol rol, LocalDateTime createdAt) {
    public UsuarioResponseDTO(Usuario u){
        this(u.getId(), u.getDni(), u.getNombres(), u.getApellidos(),
                u.getUsername(), u.getPassword(), u.getRol(), u.getCreatedAt());
    }
}
