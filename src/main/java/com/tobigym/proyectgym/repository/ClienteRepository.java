package com.tobigym.proyectgym.repository;

import java.util.List;

// import java.util.Date;
// import java.util.List;

import com.tobigym.proyectgym.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByNombres(String nombres);

    // Traer la lista de nombres y apellidos de los clientes

    List<Cliente> findByNombresAndEdad(String nombres, String edad);

    // Realizar la consulta que contenga un nombre
    @Query("SELECT c FROM Cliente c WHERE c.nombres LIKE %:Cliente%")
    List<Cliente> findByNombresContaining(@Param("Cliente") String nombres);

    // Realizar una consulta donde me ordene de forma descendente la edad
    @Query("SELECT e FROM Cliente e WHERE e.nombres =?1 ORDER BY e.edad desc")
    List<Cliente> findByNombresOrderByEdadDesc(String edad);

    Cliente findFirstById(Long id);

}