package com.proyecto.tfg.service.producto;

import com.proyecto.tfg.dto.product.ProductDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
import com.proyecto.tfg.service.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService extends Service<Product, Long> {

    void addtorestaurant(Long idRestaurant, ProductDTO productDTO)throws NotFoundException;
    Product create(ProductDTO productDTO);
    List<Product> getAll(Long idRestaurant)throws NotFoundException;
    Optional<Product> getById(Long idProduct)throws NotFoundException;
    Long productTotal();
    List<Product> findByName(String name, Pageable p) throws NotFoundException;
    Long productSearchTotal(String name);
    List<Product> findProductbyiduser(Long id,String name, Pageable p) throws NotFoundException;




}
