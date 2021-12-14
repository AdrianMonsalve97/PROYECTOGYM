package com.tobigym.proyectgym.repository.repositorycriteria;

import java.util.List;

import com.tobigym.proyectgym.models.Cliente;

public interface CLCriteriaRepository {

    List<Cliente> findByNombresAndEdad(String nombres, String edad);
}
