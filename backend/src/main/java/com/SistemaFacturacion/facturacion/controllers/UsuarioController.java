package com.SistemaFacturacion.facturacion.controllers;

import com.SistemaFacturacion.facturacion.dtos.usuario.CrearUsuarioDTO;
import com.SistemaFacturacion.facturacion.dtos.usuario.UpdateUsuarioDTO;
import com.SistemaFacturacion.facturacion.dtos.usuario.UsuarioResponseDTO;
import com.SistemaFacturacion.facturacion.dtos.usuario.UsuarioSummaryDTO;
import com.SistemaFacturacion.facturacion.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@RequestBody @Valid CrearUsuarioDTO crearUsuarioDTO){
          UsuarioResponseDTO usuarioResponseDTO = usuarioService.create(crearUsuarioDTO);
          return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioSummaryDTO>> findAll(@PageableDefault(size = 10, page = 0)
                                                           Pageable pageable) {
        return ResponseEntity.ok().body(usuarioService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody @Valid UpdateUsuarioDTO updateUsuarioDTO) {
        return ResponseEntity.ok(usuarioService.update(id, updateUsuarioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build(); // 204 status code
    }





}
