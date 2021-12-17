package com.tobigym.proyectgym.dto;

import java.util.List;

public class ClienteDto {

    private Long id;
    private String nombres;
    private String apellidos;
    private Long cedula;
    private String edad;
    private String genero;

    private InstructorDto instructor;

    // InstructorDto instructorDto;

    public ClienteDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return InstructorDto return the instructor
     */
    public InstructorDto getInstructor() {
        return instructor;
    }

    /**
     * @param instructor the instructor to set
     */
    public void setInstructor(InstructorDto instructor) {
        this.instructor = instructor;
    }

}
