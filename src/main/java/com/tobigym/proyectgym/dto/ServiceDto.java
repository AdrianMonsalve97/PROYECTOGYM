package com.tobigym.proyectgym.dto;

public class ServiceDto {

    private Long id;
    private Double precio;
    private String periodo;

    ClienteDto clienteDto;

    public ServiceDto(Long id, Double precio, String periodo, ClienteDto clienteDto) {
        this.id = id;
        this.precio = precio;
        this.periodo = periodo;
        this.clienteDto = clienteDto;
    }

    public ServiceDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public ClienteDto getClienteDto() {
        return clienteDto;
    }

    public void setClienteDto(ClienteDto clienteDto) {
        this.clienteDto = clienteDto;
    }

}
