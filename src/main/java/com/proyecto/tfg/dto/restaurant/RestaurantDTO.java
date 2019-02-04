package com.proyecto.tfg.dto.restaurant;

import com.proyecto.tfg.model.User;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantDTO {

    private Long idRestaurant;
    private String nameRestaurant;
    private String descriptionRestaurant;
//    private List<User> users;
    private Long idUser;
}
