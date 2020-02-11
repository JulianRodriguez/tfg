package com.proyecto.tfg.dto.product;

import com.proyecto.tfg.model.Restaurant;
import lombok.Data;

@Data
public class ProductDTO {

    private Long idProduct;
    private String name;
    private String description;
    private String photo;
    private String nameRestaurant;
}
