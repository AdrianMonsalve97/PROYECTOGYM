package com.tobigym.proyectgym.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tobigym.proyectgym.dto.ServiceDto;
import com.tobigym.proyectgym.models.Servicio;
import com.tobigym.proyectgym.service.ServicioService;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ServiceController {

    @Autowired
    ServicioService servicioService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("servicios")
    public ResponseEntity<List<ServiceDto>> listserv() {
        // List<Servicio> listserv = servicioService.listserv();
        // return new ResponseEntity(listserv, HttpStatus.OK);
        List<Servicio> listserv = servicioService.listserv();
        ModelMapper modelMapper = new ModelMapper();
        List<ServiceDto> res = new ArrayList<>();
        for (Servicio servicio : listserv) {
            ServiceDto clienteDto = modelMapper.map(servicio, ServiceDto.class);
            res.add(clienteDto);
        }
        return new ResponseEntity<List<ServiceDto>>(res, HttpStatus.OK);

    }

    @PostMapping("/serviciosav")
    private ResponseEntity<?> save(@RequestBody Servicio servicioDto) {
        Map<String, Object> response = new HashMap<>();

        if (servicioService.existsByPeriodo(servicioDto.getPeriodo())) {

            response.put("mensaje", "El cliente ya existe!");
            return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);

        } else {
            response.put("mensaje", "El cliente ha sido creado con éxito!");
            servicioService.save(servicioDto);

        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    // Eliminar cliente //

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deletser/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        servicioService.delete(id);
        response.put("mensaje", "El servicio ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
