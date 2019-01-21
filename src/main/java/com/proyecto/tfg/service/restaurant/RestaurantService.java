package com.proyecto.tfg.service.restaurant;

import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
import com.proyecto.tfg.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant getRestaurant(Integer idRest) throws NotFoundException;
    List<Restaurant> getAll();
    Restaurant create(RestaurantDTO Restdto);
    void addtouser(Long idUser, RestaurantDTO restaurantDTO)throws NotFoundException;
    void deleteRestaurant(Long idUser, Integer idRestaurant)throws NotFoundException;
    void addtoproduct(Restaurant restaurant, Product product);
//    List<Product> getAllProduct(Restaurant r);

}
