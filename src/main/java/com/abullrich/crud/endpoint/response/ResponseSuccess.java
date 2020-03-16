package com.abullrich.crud.endpoint.response;

import com.abullrich.crud.repository.entity.Sucursal;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Value
@Builder
@JsonInclude(NON_NULL)
public class ResponseSuccess {

    Sucursal sucursal;

    String message;
}
