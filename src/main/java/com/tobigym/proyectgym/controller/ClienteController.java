package com.tobigym.proyectgym.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tobigym.proyectgym.dto.ClienteDto;
import com.tobigym.proyectgym.models.Cliente;
import com.tobigym.proyectgym.service.ClienteService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")

public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("clientes")
    public ResponseEntity<List<ClienteDto>> list() {
        List<Cliente> list = clienteService.list();
        ModelMapper modelMapper = new ModelMapper();
        List<ClienteDto> res = new ArrayList<>();
        for (Cliente cliente : list) {
            ClienteDto clienteDto = modelMapper.map(cliente, ClienteDto.class);
            res.add(clienteDto);
        }
        return new ResponseEntity<List<ClienteDto>>(res, HttpStatus.OK);

    }

    // Crear cliente //

    @PostMapping("/clientesav")
    private ResponseEntity<?> create(@RequestBody Cliente cliente) {
        Map<String, Object> response = new HashMap<>();

        if (clienteService.existsByNombres(cliente.getNombres())) {

            response.put("mensaje", "El cliente ya existe!");
            return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);

        } else {
            response.put("mensaje", "El cliente ha sido creado con éxito!");
            clienteService.save(cliente);

        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    // Eliminar cliente //
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deletcl/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        clienteService.delete(id);
        response.put("mensaje", "El cliente ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    // Criteria ----------------------------------//
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("findAll")
    public List<Cliente> findAllCliente(@RequestParam(required = false) String nombres,
            @RequestParam(required = false) String edad) {
        return clienteService.findClientesByNombreAndEdad(nombres, edad);
    }

}
