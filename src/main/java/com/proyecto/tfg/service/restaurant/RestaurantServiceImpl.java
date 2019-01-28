package com.proyecto.tfg.service.restaurant;


import com.proyecto.tfg.dao.RestaurantDAO;
import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.model.User;
import com.proyecto.tfg.service.AbstractService;
import com.proyecto.tfg.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl extends AbstractService<Restaurant, RestaurantDAO> implements RestaurantService {

    @Autowired
    private RestaurantDAO restaurantRepository;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    @Override
    public Restaurant getRestaurant(Long idRest) throws NotFoundException {
        return restaurantRepository.findById(idRest)
                .orElseThrow(()->new NotFoundException(String.format("Restaurante no encontrado (%d)",idRest)));

    }

//    @Override
//    public List<Restaurant> getAll() {
//
//        return restaurantRepository.findAll();
//    }

    @Override
    public Restaurant create(RestaurantDTO Restdto) {

        Restaurant restaurantCrear = new Restaurant();
        restaurantCrear.setNameRestaurant(Restdto.getNameRestaurant());
        restaurantCrear.setDescriptionRestaurant(Restdto.getDescriptionRestaurant());
        return restaurantRepository.save(restaurantCrear);
    }

    @Override
    public void addtouser(Long idUser, RestaurantDTO restaurantDTO) throws NotFoundException{
        final Restaurant newRestaurant = restaurantService.create(restaurantDTO);
        userService.addrestaurant(idUser,newRestaurant);
//        return newRestaurant;
    }

    @Override
    public void deleteRestaurant(Long idUser, Long idRestaurant) throws NotFoundException {

        final User u = userService.getUser(idUser);
        final Restaurant r = restaurantService.getRestaurant(idRestaurant);
        userService.removeRestaurant(u,r);
    }

    @Override
    public void addtoproduct(Restaurant restaurant, Product product) {
        restaurant.getProduct().add(product);
        restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getAndCheck(Long id) throws NotFoundException {
        return findById(id).orElseThrow(() -> new NotFoundException("El usuario no existe"));
    }

    @Override
    public boolean isEqual(Restaurant u1, Restaurant u2) {
        return StringUtils.equals(u1.getDescriptionRestaurant(), u2.getDescriptionRestaurant()) &&
                StringUtils.equals(u1.getNameRestaurant(), u2.getNameRestaurant());
    }

    @Override
    public void setValues(Restaurant to, Restaurant from) {
        to.setDescriptionRestaurant(from.getDescriptionRestaurant());
        to.setNameRestaurant(from.getNameRestaurant());
        to.setProduct(from.getProduct());

    }
}
