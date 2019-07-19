package com.proyecto.tfg.component.mapper.product;

import com.proyecto.tfg.component.mapper.AbstractMapper;
import com.proyecto.tfg.component.mapper.restaurant.RestaurantMapper;
import com.proyecto.tfg.dto.product.ProductDTO;
import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.model.Product;
import com.proyecto.tfg.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl extends AbstractMapper<Product, ProductDTO> implements ProductMapper {

    @Override
    public Class<? extends ProductDTO> dtoClazz() {
        return ProductDTO.class;
    }

    @Override
    public Class<? extends Product> modelClazz() {
        return Product.class;
    }

    @Override
    public ProductDTO modelToDto(Product model) {
        ProductDTO dto = dozer.map(model, dtoClazz());
        return dto;
    }
}
