package com.SistemaFacturacion.facturacion.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtEncoderIntegrationTest {
    @Autowired
    private JwtEncoder jwtEncoder;

    @Test
    void smokeTestEncode() {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject("test")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .claim("scope", "test")
                .build();

        try {
            JwtEncoderParameters params = JwtEncoderParameters.from(claims);
            Jwt jwt = jwtEncoder.encode(params);

            assertNotNull(jwt.getTokenValue());
            System.out.println("✅ JWT OK -> " + jwt.getTokenValue());

        } catch (Exception ex) {
            ex.printStackTrace();
            fail("❌ Error encoding JWT: " + ex.getMessage());
        }
    }
}
