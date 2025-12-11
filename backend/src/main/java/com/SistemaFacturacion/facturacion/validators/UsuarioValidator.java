package com.SistemaFacturacion.facturacion.validators;

import com.SistemaFacturacion.facturacion.dtos.usuario.CrearUsuarioDTO;

public interface UsuarioValidator {
    void validate(CrearUsuarioDTO data);
}
