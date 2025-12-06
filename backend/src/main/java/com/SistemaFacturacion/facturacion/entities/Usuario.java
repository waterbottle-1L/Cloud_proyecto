package com.SistemaFacturacion.facturacion.entities;

import com.SistemaFacturacion.facturacion.dtos.CrearUsuarioDTO;
import com.SistemaFacturacion.facturacion.dtos.UpdateUsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dni")
    private String dni;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Rol rol;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "enabled")
    private boolean enabled = true;

    public Usuario(CrearUsuarioDTO dto){
        this.dni = dto.dni();
        this.nombres = dto.nombres();
        this.apellidos = dto.apellidos();
        this.username = dto.username();
        this.password = dto.password();
        this.rol = dto.rol();
    }

    public void update(UpdateUsuarioDTO dto){
        if (dto.dni() != null) {
            this.dni = dto.dni();
        }

        if (dto.nombres() != null) {
            this.nombres = dto.nombres();
        }

        if (dto.apellidos() != null) {
            this.apellidos = dto.apellidos();
        }

        if (dto.username() != null) {
            this.username = dto.username();
        }

        if (dto.password() != null) {
            this.password = dto.password();
        }

        if (dto.rol() != null) {
            this.rol = dto.rol();
        }
    }

}

