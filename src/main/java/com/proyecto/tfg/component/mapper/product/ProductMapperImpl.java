package com.proyecto.tfg.component.mapper.product;

import com.proyecto.tfg.component.mapper.AbstractMapper;
import com.proyecto.tfg.component.mapper.restaurant.RestaurantMapper;
import com.proyecto.tfg.dto.product.ProductDTO;
import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
import com.proyecto.tfg.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;

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



//        System.out.println("MODELO");
//        System.out.println(model.getRestaurant());
//        System.out.println("FIN MODELO");
        dto.setNameRestaurant(model.getRestaurant().getNameRestaurant());
//        System.out.println("DTO");
//        System.out.println(dto.getNameRestaurant());
//        System.out.println("FIN DTO");
        return dto;
    }

    @Override
    public Product objectToProduct(Object obj) {
        Product p = null;
//        p.setName();
//        p.setPhoto();
//        p.setDescription();
////        p.setIdProduct(obj.);
        return p;
    }

    @Override
    public ProductDTO modelToDTO(Product model) {
        ProductDTO dto = null;
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());
        dto.setPhoto(model.getPhoto());

        return dto;
    }
}
