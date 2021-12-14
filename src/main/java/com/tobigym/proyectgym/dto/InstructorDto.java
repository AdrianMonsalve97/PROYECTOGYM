package com.tobigym.proyectgym.dto;

import java.util.List;

public class InstructorDto {
    private Long id;
    private String nombres;
    private String apellidos;

    private List<ClienteDto> clienteDto;

    // ClienteDto clienteDto;

    public InstructorDto(Long id, String nombres, String apellidos, List<ClienteDto> clienteDto) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.clienteDto = clienteDto;
    }

    public InstructorDto() {

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

    public List<ClienteDto> getClienteDto() {
        return clienteDto;
    }

    public void setClienteDto(List<ClienteDto> clienteDto) {
        this.clienteDto = clienteDto;
    }

}
