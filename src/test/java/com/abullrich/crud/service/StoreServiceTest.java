package com.abullrich.crud.service;

import java.util.List;
import java.util.Optional;

import com.abullrich.crud.endpoint.response.ResponseDto;
import com.abullrich.crud.endpoint.response.ResponseError;
import com.abullrich.crud.endpoint.response.ResponseSuccess;
import com.abullrich.crud.model.Coordenadas;
import com.abullrich.crud.repository.StoreRepository;
import com.abullrich.crud.repository.entity.Sucursal;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.orm.jpa.JpaSystemException;
import reactor.test.StepVerifier;

import static com.abullrich.crud.constants.JpaExceptions.UNEXPECTED_STORE_SAVE_ERROR;
import static com.abullrich.crud.constants.JpaOperationConstants.SAVED_STORE;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

    public static Integer INVALID_STORE_ID = 0;
    public static Integer VALID_STORE_ID = 1;

    private static Sucursal STORE_1 = new Sucursal();

    private static Sucursal STORE_2 = new Sucursal();

    private List<Sucursal> StoreList = Lists.newArrayList(STORE_1, STORE_2);

    @Mock
    private StoreRepository storeRepository;

    private StoreService storeService;

    @Before
    @SneakyThrows
    public void setUp() {
        STORE_1.setId(1);
        STORE_1.setDireccion("Dorrego 2000");
        STORE_1.setLatitud(10);
        STORE_1.setLongitud(20);

        STORE_2.setId(2);
        STORE_2.setDireccion("Dorrego 4000");
        STORE_2.setLatitud(100);
        STORE_2.setLongitud(100);

        storeService = new StoreService(storeRepository);

        // TODO 16/03/2020: Will come in handy later
        // when(storeRepository.findById(INVALID_STORE_ID)).thenReturn(null);
        // when(storeRepository.findById(VALID_STORE_ID)).thenReturn(Optional.of(STORE_1));

        when(storeRepository.save(STORE_1)).thenReturn(STORE_1);
        when(storeRepository.save(STORE_2)).thenThrow(JpaSystemException.class);

        when(storeRepository.findAll()).thenReturn(StoreList);
    }

    @Test
    public void testGetClosestStore() {

        StepVerifier.create(storeService.getClosestStore(new Coordenadas(20, 30)))
            .expectNext(ResponseDto.builder()
                .result(ResponseSuccess.builder()
                    .message(null)
                    .sucursal(STORE_1)
                    .build())
                .build())
            .verifyComplete();
    }

    @Test
    public void testFailedGetClosestStore() {

        storeService.getClosestStore(new Coordenadas(20, 30))
            .doOnNext(closestStore -> assertNotEquals(closestStore.getResult().getSucursal().getId(), STORE_2.getId()))
            .subscribe();

    }

    @Test
    public void testSuccessfulStoreInsert() {
        StepVerifier.create(storeService.createStore(STORE_1))
            .expectNext(ResponseDto.builder()
                .result(ResponseSuccess.builder()
                    .message(String.format(SAVED_STORE, STORE_1.getId()))
                    .sucursal(STORE_1)
                    .build())
                .build())
            .verifyComplete();
    }

    @Test
    public void testFailedStoreInsert() {
        StepVerifier.create(storeService.createStore(STORE_2))
            .expectNext(ResponseDto.builder()
                .error(ResponseError.builder()
                    .message(String.format(UNEXPECTED_STORE_SAVE_ERROR.getMessage(), STORE_2.getDireccion(),
                        STORE_2.getLatitud(), STORE_2.getLongitud()))
                    .code(UNEXPECTED_STORE_SAVE_ERROR.getCode())
                    .build())
                .build())
            .verifyComplete();
    }
}
