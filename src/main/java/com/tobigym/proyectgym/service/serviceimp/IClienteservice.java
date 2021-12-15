package com.tobigym.proyectgym.service.serviceimp;

import java.util.List;

import com.tobigym.proyectgym.models.Cliente;

public interface IClienteservice {

    public Cliente save(Cliente clienteActual);

    List<Cliente> findClientesByNombreAndEdad(String nombres, String edad);
    Cliente findFirstById(Long id);
}
