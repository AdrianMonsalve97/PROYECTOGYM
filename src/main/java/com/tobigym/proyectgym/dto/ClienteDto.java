package com.tobigym.proyectgym.dto;

import java.util.List;

public class ClienteDto {

    private Long id;
    private String nombres;
    private String apellidos;
    private Long cedula;
    private String edad;
    private String genero;

    private List<InstructorDto> instructorDto;

    // InstructorDto instructorDto;

    public ClienteDto() {

    }

    public ClienteDto(Long id, String nombres, String apellidos, Long cedula, String edad, String genero,
            List<InstructorDto> instructorDto) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.edad = edad;
        this.genero = genero;
        this.instructorDto = instructorDto;
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

    public List<InstructorDto> getInstructorDto() {
        return instructorDto;
    }

    public void setInstructorDto(List<InstructorDto> instructorDto) {
        this.instructorDto = instructorDto;
    }

}
