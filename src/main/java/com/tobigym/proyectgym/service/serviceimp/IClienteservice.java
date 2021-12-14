package com.tobigym.proyectgym.service.serviceimp;

import java.util.List;

import com.tobigym.proyectgym.models.Cliente;

public interface IClienteservice {

    public Cliente save(Cliente clienteActual);

    List<Cliente> findByNombresAndEdad(String nombres, String edad);
}