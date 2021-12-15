package com.tobigym.proyectgym.service;

import java.util.List;

import javax.transaction.Transactional;

import com.tobigym.proyectgym.models.Cliente;
import com.tobigym.proyectgym.repository.ClienteRepository;
import com.tobigym.proyectgym.repository.repositorycriteria.CriteriaCliente;
import com.tobigym.proyectgym.service.serviceimp.IClienteservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteservice {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CriteriaCliente criteriaQuery;

    public List<Cliente> list() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    public boolean existsByNombres(String nombres) {
        return clienteRepository.existsByNombres(nombres);
    }

    @Override
    public List<Cliente> findClientesByNombreAndEdad(String nombres, String edad) {
        return criteriaQuery.findClientesByNombreAndEdad(nombres, edad);
    }
@Override
    public Cliente findFirstById(Long id) {
        return clienteRepository.findFirstById(id);
    }

}
