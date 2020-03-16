package com.abullrich.crud.endpoint.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResponseError {

    Integer code;
    String message;
}
