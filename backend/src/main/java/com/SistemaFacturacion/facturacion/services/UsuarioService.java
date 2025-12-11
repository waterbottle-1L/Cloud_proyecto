package com.SistemaFacturacion.facturacion.services;

import com.SistemaFacturacion.facturacion.dtos.usuario.CrearUsuarioDTO;
import com.SistemaFacturacion.facturacion.dtos.usuario.UpdateUsuarioDTO;
import com.SistemaFacturacion.facturacion.dtos.usuario.UsuarioResponseDTO;
import com.SistemaFacturacion.facturacion.dtos.usuario.UsuarioSummaryDTO;
import com.SistemaFacturacion.facturacion.entities.Usuario;
import com.SistemaFacturacion.facturacion.repositories.UsuarioRepository;
import com.SistemaFacturacion.facturacion.validators.UsuarioValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final List<UsuarioValidator> validators;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, List<UsuarioValidator> validators, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.validators = validators;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioResponseDTO create(CrearUsuarioDTO datos){
        validators.forEach(v->v.validate(datos));
        if (usuarioRepository.existsByUsername(datos.username())) {
            throw new IllegalArgumentException("El username ya existe");
        }

        Usuario usuario = new Usuario(datos);

        //hashear contraseña antes de persistir
        if (datos.password() != null) {
            usuario.setPassword(passwordEncoder.encode(datos.password()));
        } else {
            throw new IllegalArgumentException("Password es obligatorio");
        }

        Usuario saved = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public Page<UsuarioSummaryDTO> findAll(Pageable pageable){
        return  usuarioRepository.findAll(pageable).map(UsuarioSummaryDTO::new);
    }

    @Transactional(readOnly = true)
    public UsuarioSummaryDTO findById(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
                "El usuario con id " + id + " no fue encontrado"));

        return new UsuarioSummaryDTO((usuario));
    }

    @Transactional
    public UsuarioResponseDTO update(Long id, UpdateUsuarioDTO datos){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El usuario con id " + id + " no fue encontrado"));

        // hashear password antes de actualizar
        String hashed = null;
        if (datos.password() != null) {
            hashed = passwordEncoder.encode(datos.password());
        }

        usuario.update(datos); // actualiza campos

        if (hashed != null ) {
            usuario.setPassword(hashed);
        }

        Usuario saved = usuarioRepository.save(usuario); // save recommended to ensure flush
        return new UsuarioResponseDTO(saved);
    }

    @Transactional
    public void delete(Long id){
        if(!usuarioRepository.existsById(id)){
            throw  new EntityNotFoundException("El usuario con id " + id + " no fue encontrado");
        }

        usuarioRepository.deleteById(id);
    }
    // Método útil para autenticación / UserDetailsService
    @Transactional(readOnly = true)
    public Usuario findEntityByUsername(String username){
        return usuarioRepository.findByUsername(username).orElse(null);
    }


}
