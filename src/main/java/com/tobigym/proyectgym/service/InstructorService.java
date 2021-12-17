package com.tobigym.proyectgym.service;

import java.util.List;
import com.tobigym.proyectgym.models.Instructor;
import com.tobigym.proyectgym.repository.InstructorRepository;
import com.tobigym.proyectgym.service.serviceimp.IInstructorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService implements IInstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    public List<Instructor> listins() {
        return instructorRepository.findAll();
    }

    public void save(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    // public Instructor save(Instructor instructor) {
    // return instructorRepository.save(instructor);
    // }

    public void delete(Long id) {
        instructorRepository.deleteById(id);
    }

    public boolean existsByNombres(String nombres) {
        return instructorRepository.existsByNombres(nombres);
    }

    public Instructor findFirstById(Long id) {
        return instructorRepository.findFirstById(id);
    }

}
