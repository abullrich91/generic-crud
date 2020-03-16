package com.abullrich.crud.endpoint;

import com.abullrich.crud.endpoint.response.ResponseDto;
import com.abullrich.crud.model.Coordenadas;
import com.abullrich.crud.repository.entity.Sucursal;
import com.abullrich.crud.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Generic CRUD REST API")
@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class CrudEndpoint {

    private final StoreService storeService;

    @ApiOperation(value = "Retrieves a store by storeId")
    @GetMapping(produces = APPLICATION_JSON_VALUE, params = {"storeId"})
    public Mono<ResponseDto> getStore(@RequestParam Integer storeId) {
        return storeService.getStoreById(storeId);
    }

    @ApiOperation(value = "Retrieves closest store to a given latitude and longitude")
    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/closest")
    public Mono<ResponseDto> getClosestStore(@RequestBody Coordenadas coordenadas) {
        return storeService.getClosestStore(coordenadas);
    }

    @ApiOperation(value = "Creates a store")
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public Mono<ResponseDto> createStore(@RequestBody Sucursal sucursal) {
        return storeService.createStore(sucursal);
    }

    @ApiOperation(value = "Update a store")
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public Mono<ResponseDto> updateStore(@RequestBody Sucursal sucursal) {
        return storeService.updateStore(sucursal);
    }
}
