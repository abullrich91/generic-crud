package com.abullrich.crud.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.abullrich.crud.constants.JpaOperationConstants.ADDITIONAL_INFO;

@Getter
@AllArgsConstructor
public enum JpaExceptions {

    UNEXPECTED_STORE_SAVE_ERROR(5001, "Error al guardar sucursal con direccion: %s, latitud: %d, longitud: %d " + ADDITIONAL_INFO),

    UNEXPECTED_STORE_UPDATE_ERROR(50002, "Error al actualizar sucursal con id: %d. " + ADDITIONAL_INFO),

    UNEXPECTED_GET_STORE_ERROR(50003, "Error inesperado al obtener Sucursal con id: %d. " + ADDITIONAL_INFO),

    UNEXPECTED_GET_CLOSEST_STORE_ERROR(50003, "Error inesperado al buscar sucursales");

    private Integer code;
    private String message;
}
