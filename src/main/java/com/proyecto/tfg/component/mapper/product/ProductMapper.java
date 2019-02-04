package com.proyecto.tfg.component.mapper.product;

import com.proyecto.tfg.dto.product.ProductDTO;
import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
import com.proyecto.tfg.component.mapper.Mapper;
import com.proyecto.tfg.model.Restaurant;

public interface ProductMapper extends Mapper<Product, ProductDTO> {

    Product dtoToModel(ProductDTO dto) throws NotFoundException;

}

