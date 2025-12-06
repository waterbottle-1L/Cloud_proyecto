package com.SistemaFacturacion.facturacion.security;

import com.SistemaFacturacion.facturacion.entities.Usuario;
import com.SistemaFacturacion.facturacion.repositories.UsuarioRepository;
import com.SistemaFacturacion.facturacion.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UsuarioService usuarioService;

    public CustomUserDetailService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario u = usuarioService.findEntityByUsername(username);
        if(u==null) throw new UsernameNotFoundException("Usuario no encontrado");

        List<SimpleGrantedAuthority> authorities = u.getRol() != null
                ? List.of(new SimpleGrantedAuthority("ROLE_" + u.getRol().name()))
                : List.of();

        return User.builder()
                .username(u.getUsername())
                .password(u.getPassword()) // debe estar el hash
                .authorities(authorities)
                .accountLocked(false)
                .disabled(!u.isEnabled())
                .build();
    }


}
