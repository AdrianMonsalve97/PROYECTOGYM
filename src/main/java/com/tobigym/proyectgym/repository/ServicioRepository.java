package com.tobigym.proyectgym.repository;

import com.tobigym.proyectgym.models.Servicio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    public boolean existsByPeriodo(String periodo);

    //
}
