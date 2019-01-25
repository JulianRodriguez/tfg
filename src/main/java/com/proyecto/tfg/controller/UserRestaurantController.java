package com.proyecto.tfg.controller;

import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/user/{idUser}/restaurant")
public class UserRestaurantController {

//    @Autowired
//    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @PostMapping
    public void create(@RequestBody RestaurantDTO restaurantDTO,
                   @PathVariable("idUser") Long idUser)throws NotFoundException {
//        Restaurant createRestaurant = restaurantService.addtouser(idUser,restaurantDTO);
        restaurantService.addtouser(idUser,restaurantDTO);
//        userService.addrestaurant(idUser,createRestaurant);
    }
//    @GetMapping
//    public List<Restaurant> getAll() {
//        return restaurantService.findAll();
//    }

    @GetMapping("/{idRestaurant}")
    public Restaurant getById(@PathVariable("idRestaurant") Long idRestaurant) throws NotFoundException {
        return restaurantService.getRestaurant(idRestaurant);
    }

    @DeleteMapping("/{idRestaurant}")
    public void delete(@PathVariable("idUser") Long iduser,
                                  @PathVariable("idRestaurant") Long idrestaurant)throws NotFoundException
    {
        restaurantService.deleteRestaurant(iduser,idrestaurant);
    }

//    @RequestMapping(value = "/{idUser}/restaurant/{idRestaurant}", method = RequestMethod.GET)
//    public void addRestUser(@PathVariable Integer idUser, @PathVariable Integer idRestaurant) throws UserNotFoundException, RestaurantNotFoundException {
//        restaurantService.addRestaurantUser(idUser,idRestaurant);
//    }


}
