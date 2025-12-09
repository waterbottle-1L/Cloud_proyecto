package com.SistemaFacturacion.facturacion.config;

import org.junit.jupiter.api.Test;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.springframework.security.oauth2.jwt.*;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.SecurityContext;

import static org.junit.jupiter.api.Assertions.*;

public class LocalNimbusEncoderTest {
    @Test
    void localEncoderShouldEncodeWithSameSecret() {
        // Copia EXACTAMENTE el valor de tu property jwt.secret
        String base64Secret = "Bjd9gRtAymjvrH6KHW34Q8es08DTJHFZg1HoHdsYVwM=";

        byte[] keyBytes = Base64.getDecoder().decode(base64Secret);
        System.out.println("local secret bytes length -> " + keyBytes.length); // debe ser 32

        SecretKey secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");

        // Construimos un encoder LOCAL idéntico al bean esperado
        JwtEncoder localEncoder = new NimbusJwtEncoder(new ImmutableSecret<SecurityContext>(secretKey));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject("local-test")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .claim("scope", "test")
                .build();

        JwtEncoderParameters params = JwtEncoderParameters.from(claims);

        // Intentamos codificar
        Jwt jwt = localEncoder.encode(params);
        assertNotNull(jwt.getTokenValue());
        System.out.println("✅ local JWT OK -> " + jwt.getTokenValue());
    }
}
