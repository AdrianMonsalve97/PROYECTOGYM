package com.tobigym.proyectgym.repository;

import com.tobigym.proyectgym.models.Usuariojwt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuariojwt, Long> {

    // Usuariojwt findByUsuario(String username);
    Usuariojwt findByUsuario(String username);
}
