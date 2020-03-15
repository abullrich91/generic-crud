package com.abullrich.crud.endpoint;

import com.abullrich.crud.model.Sucursal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Generic CRUD REST API")
@RestController
@RequestMapping("/api/store")
public class CrudEndpoint {

    @ApiOperation(value = "Retrieves a store by storeId")
    @GetMapping(produces = APPLICATION_JSON_VALUE, params = {"storeId"})
    public Sucursal getStore(@RequestParam String storeId) {
        return null;
    }

    @ApiOperation(value = "Creates a store")
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public Sucursal createStore(@RequestBody Sucursal sucursal) {
        return null;
    }

    @ApiOperation(value = "Update a store")
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public Sucursal updateStore(@RequestBody Sucursal sucursal) {
        return null;
    }
}
