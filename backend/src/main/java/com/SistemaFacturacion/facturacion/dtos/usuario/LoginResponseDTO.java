package com.SistemaFacturacion.facturacion.dtos;

public record LoginResponseDTO(String accessToken, String tokenType, Long expiresIn) {
}
