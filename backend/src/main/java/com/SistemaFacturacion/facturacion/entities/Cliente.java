package com.SistemaFacturacion.facturacion.entities;


import com.SistemaFacturacion.facturacion.dtos.CrearClienteDTO;
import com.SistemaFacturacion.facturacion.dtos.UpdateClienteDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "clientes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"dni"}))
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres")
    private String nombres;

    private String apellidos;

    @Column(name = "documento")
    private TipoDocumento tipoDocumento;

    @Column(name="numero")
    private String numeroDocumento;

    @Column(name="direccion")
    private String direccion;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Cliente(CrearClienteDTO dto){
        this.nombres = dto.nombres();
        this.apellidos = dto.apellidos();
        this.direccion = dto.direccion();
        this.tipoDocumento = dto.tipoDocumento();
        this.numeroDocumento = dto.numeroDocumento();
    }

    public void update(UpdateClienteDTO dto){
        if (dto.nombres() != null){
            this.nombres = dto.nombres();
        }

        if (dto.apellidos() != null){
            this.apellidos = dto.apellidos();
        }

        if (dto.direccion() != null) {
            this.direccion = dto.direccion();
        }

        if (dto.tipoDocumento() != null) {
            this.tipoDocumento = dto.tipoDocumento();
        }

        if(dto.numeroDocumento() != null) {
            this.numeroDocumento = dto.numeroDocumento();
        }
    }
}
