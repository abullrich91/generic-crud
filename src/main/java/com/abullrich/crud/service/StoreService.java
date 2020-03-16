package com.abullrich.crud.service;

import java.util.List;
import java.util.Optional;

import com.abullrich.crud.endpoint.response.ResponseDto;
import com.abullrich.crud.endpoint.response.ResponseError;
import com.abullrich.crud.endpoint.response.ResponseSuccess;
import com.abullrich.crud.model.Coordenadas;
import com.abullrich.crud.repository.StoreRepository;
import com.abullrich.crud.repository.entity.Sucursal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.abullrich.crud.constants.JpaExceptions.UNEXPECTED_GET_CLOSEST_STORE_ERROR;
import static com.abullrich.crud.constants.JpaExceptions.UNEXPECTED_GET_STORE_ERROR;
import static com.abullrich.crud.constants.JpaExceptions.UNEXPECTED_STORE_SAVE_ERROR;
import static com.abullrich.crud.constants.JpaExceptions.UNEXPECTED_STORE_UPDATE_ERROR;
import static com.abullrich.crud.constants.JpaOperationConstants.EMPTY_RESULT_SET;
import static com.abullrich.crud.constants.JpaOperationConstants.SAVED_STORE;
import static com.abullrich.crud.constants.JpaOperationConstants.UPDATED_STORE;

@Service
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(final StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Mono<ResponseDto> getStoreById(final Integer storeId) {

        return Mono.fromCallable(() -> storeRepository.findById(storeId)
            .map(store -> buildSuccessResponse(null, store))
            .orElse(null))
            .onErrorResume(error -> {
                String errorMessage = String.format(UNEXPECTED_GET_STORE_ERROR.getMessage(), storeId);
                return buildErrorResponse(UNEXPECTED_GET_STORE_ERROR.getCode(), errorMessage, error);
            }).switchIfEmpty(buildEmptyResponse(String.format(EMPTY_RESULT_SET, storeId)));
    }

    public Mono<ResponseDto> getClosestStore(final Coordenadas coordenadas) {

        return Mono.fromCallable(storeRepository::findAll)
            .map(stores -> findClosestStore(stores, coordenadas))
            .onErrorResume(error -> buildErrorResponse(UNEXPECTED_GET_STORE_ERROR.getCode(), UNEXPECTED_GET_CLOSEST_STORE_ERROR.getMessage(), error))
            .switchIfEmpty(buildEmptyResponse(UNEXPECTED_GET_CLOSEST_STORE_ERROR.getMessage()));
    }

    // TODO 15/03/2020: This can be further optimized using database ordering and search algorithms.
    private ResponseDto findClosestStore(final List<Sucursal> stores, final Coordenadas coordenadas) {
        Integer smallestTotal = Integer.MAX_VALUE;
        Sucursal closestStore = null;

        for (Sucursal store : stores) {
            Integer latitudeDifference = Math.abs(store.getLatitud() - coordenadas.getLatitud());
            Integer longitudeDifference = Math.abs(store.getLongitud() - coordenadas.getLongitud());
            Integer totalDifference = latitudeDifference + longitudeDifference;

            if (totalDifference < smallestTotal) {
                smallestTotal = totalDifference;
                closestStore = store;
            }
        }

        return buildSuccessResponse(null, closestStore);
    }

    public Mono<ResponseDto> createStore(final Sucursal sucursal) {

        return Mono.fromCallable(() -> storeRepository.save(sucursal))
            .map(savedStore -> buildSuccessResponse(String.format(SAVED_STORE, savedStore.getId()), savedStore))
            .onErrorResume(error -> {
                String errorMessage = String.format(UNEXPECTED_STORE_SAVE_ERROR.getMessage(), sucursal.getDireccion(),
                    sucursal.getLatitud(), sucursal.getLongitud());
                return buildErrorResponse(UNEXPECTED_STORE_SAVE_ERROR.getCode(), errorMessage, error);
            });
    }

    public Mono<ResponseDto> updateStore(final Sucursal sucursal) {

        return Mono.fromCallable(() -> {
            storeRepository.updateStore(sucursal.getDireccion(), sucursal.getLatitud(),
                sucursal.getLongitud(), sucursal.getId());
            return sucursal;
        }).map(updatedStore -> buildSuccessResponse(String.format(UPDATED_STORE, updatedStore.getId()), updatedStore))
            .onErrorResume(error -> {
                String errorMessage = String.format(UNEXPECTED_STORE_UPDATE_ERROR.getMessage(), sucursal.getId());
                return buildErrorResponse(UNEXPECTED_STORE_UPDATE_ERROR.getCode(), errorMessage, error);
            });
    }

    private ResponseDto buildSuccessResponse(final String successMessage,
                                             final Sucursal store) {

        log.debug(successMessage);
        return ResponseDto.builder()
            .result(ResponseSuccess.builder()
                .message(successMessage)
                .sucursal(store)
                .build())
            .build();
    }

    private Mono<ResponseDto> buildErrorResponse(final Integer errorCode,
                                                 final String errorMessage,
                                                 final Throwable error) {

        log.error(errorMessage, error);
        return Mono.just(ResponseDto.builder()
            .error(ResponseError.builder()
                .code(errorCode)
                .message(errorMessage)
                .build())
            .build());
    }

    private Mono<ResponseDto> buildEmptyResponse(final String message) {

        log.debug(message);
        return Mono.just(ResponseDto.builder()
            .result(ResponseSuccess.builder()
                .message(message)
                .sucursal(null)
                .build())
            .build());
    }
}
