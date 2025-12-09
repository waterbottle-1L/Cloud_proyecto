package com.SistemaFacturacion.facturacion.services;

import com.SistemaFacturacion.facturacion.dtos.ClienteResponseDTO;
import com.SistemaFacturacion.facturacion.dtos.ClienteSummaryDTO;
import com.SistemaFacturacion.facturacion.dtos.CrearClienteDTO;
import com.SistemaFacturacion.facturacion.dtos.UpdateClienteDTO;
import com.SistemaFacturacion.facturacion.entities.Cliente;
import com.SistemaFacturacion.facturacion.repositories.ClienteRepository;

import com.SistemaFacturacion.facturacion.validators.ClienteValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private List<ClienteValidator> validators;


    @Autowired
    public ClienteService(ClienteRepository clienteRepository, List<ClienteValidator> validators){
        this.clienteRepository = clienteRepository;
        this.validators = validators;
    }

    public ClienteResponseDTO create(CrearClienteDTO datos){
        validators.forEach(v->v.validate(datos));

        Cliente cliente = new Cliente(datos);
        return new ClienteResponseDTO(clienteRepository.save(cliente));
    }

    public Page<ClienteSummaryDTO> findAll(Pageable pageable){
        return clienteRepository.findAll(pageable).map(ClienteSummaryDTO::new);
    }


    public ClienteSummaryDTO findById(Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
                "Este ID para el usuario para el usuario no fue encontrado: " + id));

        return new ClienteSummaryDTO(cliente);
    }

    public ClienteResponseDTO update(Long id, UpdateClienteDTO datos) {
        if(!clienteRepository.existsById(id)) {
            throw  new EntityNotFoundException("Este id para el usuario no fue encontrado");
        }
        Cliente cliente = clienteRepository.getReferenceById(id);
        cliente.update(datos);
        return new ClienteResponseDTO(cliente);
    }

    public void delete(Long id) {
        if (!clienteRepository.existsById(id)){
            throw new EntityNotFoundException("Este id para el usuario no fue encontrado");
        }

        Cliente cliente = clienteRepository.getReferenceById(id);
        clienteRepository.delete(cliente);
    }

}
