package com.tobigym.proyectgym.repository.repositorycriteria;

import java.util.List;

import com.tobigym.proyectgym.models.Cliente;

public interface CLCriteriaRepository {

    List<Cliente> findClientesByNombreAndEdad(String nombres, String edad);
}
