package com.tobigym.proyectgym.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tobigym.proyectgym.dto.InstructorDto;
import com.tobigym.proyectgym.models.Instructor;
import com.tobigym.proyectgym.service.InstructorService;

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

// @Secured("ROLE_ADMIN")
@RestController
@RequestMapping("api")
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    // Consultar instructor
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("instructores")
    public ResponseEntity<List<InstructorDto>> listins() {
        List<Instructor> listins = instructorService.listins();
        ModelMapper modelMapper = new ModelMapper();
        List<InstructorDto> res = new ArrayList<>();
        for (Instructor instructor : listins) {
            InstructorDto clienteDto = modelMapper.map(instructor, InstructorDto.class);
            res.add(clienteDto);
        }
        return new ResponseEntity<List<InstructorDto>>(res, HttpStatus.OK);

    }

    // Crear instructor

    @PostMapping("/instructorsav")
    private ResponseEntity<?> save(@RequestBody Instructor instructorDto) {
        Map<String, Object> response = new HashMap<>();

        if (instructorService.existsByNombres(instructorDto.getNombres())) {

            response.put("mensaje", "El cliente ya existe!");
            return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);

        } else {
            response.put("mensaje", "El cliente ha sido creado con éxito!");
            instructorService.save(instructorDto);

        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    // Eliminar Instructor
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deletins/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        instructorService.delete(id);
        response.put("mensaje", "El instructor ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
