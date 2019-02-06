package com.proyecto.tfg.dao;

import com.proyecto.tfg.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO  extends GenericDAO<Product> {

    @Query(value = "select count(idProduct) from Product ")
    Long productTotales();
}
