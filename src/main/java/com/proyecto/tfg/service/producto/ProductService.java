package com.proyecto.tfg.service.producto;

import com.proyecto.tfg.dto.product.ProductDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void addtorestaurant(Long idRestaurant, ProductDTO productDTO)throws NotFoundException;
    Product create(ProductDTO productDTO);
    List<Product> getAll(Long idRestaurant)throws NotFoundException;
    Optional<Product> getById(Long idProduct)throws NotFoundException;


}
