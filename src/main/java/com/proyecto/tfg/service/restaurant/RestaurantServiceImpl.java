package com.proyecto.tfg.service.restaurant;


import com.proyecto.tfg.dao.RestaurantDAO;
import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.model.User;
import com.proyecto.tfg.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantDAO restaurantRepository;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    @Override
    public Restaurant getRestaurant(Integer idRest) throws NotFoundException {
        return restaurantRepository.findById(idRest)
                .orElseThrow(()->new NotFoundException(String.format("Restaurante no encontrado (%d)",idRest)));

    }

    @Override
    public List<Restaurant> getAll() {

        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant create(RestaurantDTO Restdto) {

        Restaurant restaurantCrear = new Restaurant();
        restaurantCrear.setNameRestaurant(Restdto.getName());
        restaurantCrear.setDescriptionRestaurant(Restdto.getDescription());
        return restaurantRepository.save(restaurantCrear);
    }

    @Override
    public void addtouser(Long idUser, RestaurantDTO restaurantDTO) throws NotFoundException{
        final Restaurant newRestaurant = restaurantService.create(restaurantDTO);
        userService.addrestaurant(idUser,newRestaurant);
//        return newRestaurant;
    }

    @Override
    public void deleteRestaurant(Long idUser, Integer idRestaurant) throws NotFoundException {

        final User u = userService.getUser(idUser);
        final Restaurant r = restaurantService.getRestaurant(idRestaurant);
        userService.removeRestaurant(u,r);
    }

    @Override
    public void addtoproduct(Restaurant restaurant, Product product) {
        restaurant.getProduct().add(product);
        restaurantRepository.save(restaurant);
    }
}
