package com.SistemaFacturacion.facturacion.controllers;

import com.SistemaFacturacion.facturacion.dtos.cliente.ClienteResponseDTO;
import com.SistemaFacturacion.facturacion.dtos.cliente.ClienteSummaryDTO;
import com.SistemaFacturacion.facturacion.dtos.cliente.CrearClienteDTO;
import com.SistemaFacturacion.facturacion.dtos.cliente.UpdateClienteDTO;
import com.SistemaFacturacion.facturacion.services.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> createCLient(@RequestBody @Valid CrearClienteDTO crearClienteDTO) {
        ClienteResponseDTO clienteResponseDTO = clienteService.create(crearClienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponseDTO); // 201 status code
    }

    @GetMapping
    public ResponseEntity<Page<ClienteSummaryDTO>> getAllClients(@PageableDefault(size = 10, page = 0)
                                                                 Pageable pageable) {
        return ResponseEntity.ok(clienteService.findAll(pageable)); //200 status code
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClienteResponseDTO> updateCliente(@PathVariable Long id,
                                                            @RequestBody @Valid UpdateClienteDTO updateClienteDTO) {
        return ResponseEntity.ok(clienteService.update(id, updateClienteDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> deleteCliente(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build(); // 204 status
    }

}
