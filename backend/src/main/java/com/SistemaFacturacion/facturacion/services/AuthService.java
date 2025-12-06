package com.SistemaFacturacion.facturacion.services;

import com.SistemaFacturacion.facturacion.dtos.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    private final long jwtExpirationSeconds;

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

        JwtEncoderParameters params = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(params).getTokenValue();

    }

    public LoginResponseDTO authenticateAndGetTokenResponse(String username, String pasword){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, pasword)
        );

        String token = createTokenForAuthentication(authentication);
        return new LoginResponseDTO(token, "Bearer", jwtExpirationSeconds);
    }
}
