package com.SistemaFacturacion.facturacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                      PasswordEncoder passwordEncoder){
       DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
       provider.setPasswordEncoder(passwordEncoder);
       return new ProviderManager(List.of(provider));
   }

   private JwtAuthenticationConverter jwtAuthenticationConverter() {
       JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
       grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
       // Prefijo ROLE_ para compatibilidad con hasRole(...)
       grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

       JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
       converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
       return converter;
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .cors(Customizer.withDefaults())
               .csrf(AbstractHttpConfigurer::disable)
               .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers("/api/auth/login","/api/usuarios", "/actuator/health", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                       .anyRequest().authenticated()
               )
               .oauth2ResourceServer(oauth2 -> oauth2
                       .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
               );
       return http.build();
   }

   @Bean
   public CorsConfigurationSource corsConfigurationSource(){
       CorsConfiguration cfg = new CorsConfiguration();
       cfg.setAllowedOrigins(List.of("http://localhost:3000")); // ajustar a tu front
       cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
       cfg.setAllowedHeaders(List.of("*"));
       cfg.setExposedHeaders(List.of("Authorization"));
       cfg.setAllowCredentials(false);
       cfg.setMaxAge(3600L);

       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**", cfg);
       return source;
   }


}
