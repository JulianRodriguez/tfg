package com.proyecto.tfg.dao;

import com.proyecto.tfg.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO  extends GenericDAO<Product> {

    @Query(value = "select count(idProduct) from Product ")
    Long productTotales();

    @Query(value = "select u from Product AS u where LOWER(u.name) LIKE %:name%")
    List<Product> findByName (@Param("name") String name, Pageable pageable);

    @Query(value = "select count(u) from Product AS u where LOWER(u.name) LIKE %:name%")
    Long productSearchTotal(@Param("name") String name);
}
