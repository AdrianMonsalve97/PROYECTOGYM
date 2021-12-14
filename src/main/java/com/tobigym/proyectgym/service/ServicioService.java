package com.tobigym.proyectgym.service;

import java.util.List;

import com.tobigym.proyectgym.models.Servicio;
import com.tobigym.proyectgym.repository.ServicioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioService {

    @Autowired
    ServicioRepository servicioRepository;

    public List<Servicio> listserv() {
        return servicioRepository.findAll();
    }

    public void save(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    public void delete(Long id) {
        servicioRepository.deleteById(id);
    }

    public boolean existsByPeriodo(String periodo) {
        return servicioRepository.existsByPeriodo(periodo);
    }
}
