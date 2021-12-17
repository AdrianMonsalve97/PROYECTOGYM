package com.tobigym.proyectgym.repository;

import java.util.List;

import com.tobigym.proyectgym.models.Instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("SELECT i FROM Instructor i")
    List<Instructor> findAll();

    boolean existsByNombres(String nombres);

    Instructor findFirstById(Long id);
    
}
