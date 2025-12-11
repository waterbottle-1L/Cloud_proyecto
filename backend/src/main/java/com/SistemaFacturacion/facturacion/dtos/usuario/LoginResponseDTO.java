package com.SistemaFacturacion.facturacion.dtos.usuario;

public record LoginResponseDTO(String accessToken, String tokenType, Long expiresIn) {
}
