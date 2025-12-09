package com.SistemaFacturacion.facturacion.services;

import com.SistemaFacturacion.facturacion.dtos.LoginResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    private final long jwtExpirationSeconds;

    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthService(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, @Value("${security.jwt.expiration-seconds:3600}") long jwtExpirationSeconds){
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.jwtExpirationSeconds = jwtExpirationSeconds;
    }

    public String authenticationAndGetToken(String username, String password){
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password)
        );

        return createTokenForAuthentication(authentication);
    }

    private String createTokenForAuthentication(Authentication authentication){
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(jwtExpirationSeconds);

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(role -> {
                    if(role.startsWith("ROLE_")) return role.substring(5);
                    return role;
                })
                .collect(Collectors.toList());

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("com.SistemaFacturacion.facturacion") // ajusta si quieres
                .issuedAt(now)
                .expiresAt(exp)
                .subject(authentication.getName())
                .claim("roles", roles) // lista de roles -> JwtGrantedAuthoritiesConverter leerá este claim
                .build();
        JwsHeader headers = JwsHeader.with(MacAlgorithm.HS256).build();
        logger.debug("Encoding JWT - header alg={}, kid={}", headers.getAlgorithm(), headers.getKeyId());

        JwtEncoderParameters params = JwtEncoderParameters.from(headers, claims);

        try {
            Jwt jwt = jwtEncoder.encode(params);
            logger.debug("JWT created for {} (len={})", authentication.getName(), jwt.getTokenValue().length());
            return jwt.getTokenValue();
        } catch (Exception e) {
            logger.error("Error encoding JWT for user {}", authentication.getName(), e);
            throw e; // o envuelve en una RuntimeException si prefieres
        }


    }

    public LoginResponseDTO authenticateAndGetTokenResponse(String username, String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = createTokenForAuthentication(authentication);
        return new LoginResponseDTO(token, "Bearer", jwtExpirationSeconds);
    }
}
