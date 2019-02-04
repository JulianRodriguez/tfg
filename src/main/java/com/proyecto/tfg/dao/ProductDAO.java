package com.proyecto.tfg.dao;

import com.proyecto.tfg.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO  extends GenericDAO<Product> {
}
