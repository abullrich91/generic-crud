package com.abullrich.crud.model;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordenadas {

    @Column(name = "latitud")
    Integer latitud;

    @Column(name = "longitud")
    Integer longitud;
}
