package com.abullrich.crud.model;

import javax.persistence.Column;

import lombok.Data;

@Data
public class Coordenadas {

    @Column(name = "latitud")
    Integer latitud;

    @Column(name = "longitud")
    Integer longitud;
}
