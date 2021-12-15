package com.tobigym.proyectgym.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tobigym.proyectgym.dto.ClienteDto;
import com.tobigym.proyectgym.dto.Mensaje;
import com.tobigym.proyectgym.models.Cliente;
import com.tobigym.proyectgym.models.Instructor;
import com.tobigym.proyectgym.service.ClienteService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.tobigym.proyectgym.service.InstructorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.commons.lang3.StringUtils;

@RestController
@RequestMapping("api")

public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    InstructorService instructorService;

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

    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
        if (StringUtils.isBlank(clienteDto.getApellidos()))
            return new ResponseEntity(new Mensaje("el apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        else if (clienteService.existsByNombres(clienteDto.getNombres()))
            return new ResponseEntity(new Mensaje("nombre ya existe"), HttpStatus.BAD_REQUEST);
        Cliente cliente = new Cliente(clienteDto.getId(),clienteDto.getNombres(), clienteDto.getApellidos(), clienteDto.getCedula(),
                clienteDto.getEdad(), clienteDto.getGenero());
        clienteService.save(cliente);
        return new ResponseEntity(new Mensaje("cliente creado"), HttpStatus.OK);
    }

    // convertir cliente a list
    private ArrayList<Cliente> obtenerCliente(HttpServletRequest request) {
        ArrayList<Cliente> cliente = (ArrayList<Cliente>) request.getSession()
                .getAttribute("cliente");
        if (cliente == null) {
            cliente = new ArrayList<>();
        }
        return cliente;
    }

    private void guardarCliente(List<Cliente> cliente, HttpServletRequest request) {
        request.getSession().setAttribute("cliente", cliente);
    }

    @PostMapping(value = "/AsigagnarCliente/{id}")
    public String asignarTarea(@ModelAttribute ClienteDto clienteDto, HttpServletRequest request,
            RedirectAttributes redirectAttrs) {

        ArrayList<Cliente> clientes = this.obtenerCliente(request);
        Cliente clientePorId = clienteService.findFirstById(clienteDto.getId());
        if (clientePorId == null) {
            redirectAttrs.addFlashAttribute("mensaje", "El usuario con el id " + clienteDto.getId() + " no existe")
                    .addFlashAttribute("clase", "warning");
            return "Usuario no existe";
        }
        boolean encontrado = false;
        for (Cliente clienteParaAsignarActual : clientes) {
            if (clienteParaAsignarActual.getId().equals(clienteParaAsignarActual.getId())) {
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            clientes.add(new Cliente(clientePorId.getNombres(), clientePorId.getApellidos(), clientePorId.getCedula(),
                    clientePorId.getEdad(), clientePorId.getGenero()));
        }

        this.guardarCliente(clientes, request);
        return "usuario agregado " + clientes;

    }

    private ArrayList<Cliente> obtenerUsaurio(HttpServletRequest request) {
        return null;
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
