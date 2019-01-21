package com.proyecto.tfg.dto.restaurant;

import com.proyecto.tfg.model.User;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantDTO {

    private Long idRestaurant;
    private String name;
    private String description;
    private List<User> users;
}
