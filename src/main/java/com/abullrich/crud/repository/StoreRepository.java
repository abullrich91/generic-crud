package com.abullrich.crud.repository;

import javax.transaction.Transactional;

import com.abullrich.crud.repository.entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StoreRepository extends JpaRepository<Sucursal, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Sucursal s SET s.direccion = ?1, s.latitud = ?2, s.longitud = ?3 where s.id = ?4")
    void updateStore(final String direccion, final Integer latitud, final Integer longitud, final Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Sucursal s SET s.direccion = ?1 where s.id = ?2")
    void updateStoreAdress(final String direccion, final Integer id);

}
