package com.proyecto.tfg.service.restaurant;

import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.service.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService extends Service<Restaurant, Long> {
    Restaurant getRestaurant(Long idRest) throws NotFoundException;
//    List<Restaurant> getAll();
    Restaurant create(RestaurantDTO Restdto);
    void addtouser(Long idUser, RestaurantDTO restaurantDTO)throws NotFoundException;
    void deleteRestaurant(Long idUser, Long idRestaurant)throws NotFoundException;
    void addtoproduct(Restaurant restaurant, Product product);
    List<Restaurant> findRestaurantbyiduser(Long id, Pageable p) throws NotFoundException;
//    List<Product> getAllProduct(Restaurant r);
    Long restaurantTotal();

}
