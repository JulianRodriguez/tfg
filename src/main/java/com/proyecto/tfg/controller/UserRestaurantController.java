package com.proyecto.tfg.controller;

import com.proyecto.tfg.component.mapper.restaurant.RestaurantMapper;
import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/user/{idUser}/restaurant")
public class UserRestaurantController {

//    @Autowired
//    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    RestaurantMapper restaurantMapper;

    @PostMapping
    public void create(@RequestBody RestaurantDTO restaurantDTO,
                   @PathVariable("idUser") Long idUser)throws NotFoundException {
//        Restaurant createRestaurant = restaurantService.addtouser(idUser,restaurantDTO);
        restaurantService.addtouser(idUser,restaurantDTO);
//        userService.addrestaurant(idUser,createRestaurant);
    }

    @GetMapping("/{idRestaurant}")
    public Restaurant getById(@PathVariable("idRestaurant") Long idRestaurant) throws NotFoundException {
        return restaurantService.getRestaurant(idRestaurant);
    }


    @GetMapping
    public List<RestaurantDTO> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                       @RequestParam(defaultValue = "10", required= false ) Integer size,
                                       @PathVariable("idUser") Long idUser) throws NotFoundException{
        final List<Restaurant> result = restaurantService.findRestaurantbyiduser(idUser, PageRequest.of(page, size));
        System.out.println(result);
        return restaurantMapper.modelToDto(result);
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
