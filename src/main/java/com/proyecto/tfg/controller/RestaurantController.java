package com.proyecto.tfg.controller;

import com.proyecto.tfg.component.mapper.restaurant.RestaurantMapper;
import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.InvalidRequestException;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/restaurant")
public class RestaurantController extends AbstractController<Restaurant, RestaurantDTO>{

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/total")
    public Long findTotal() throws NotFoundException {
        final Long totaldeRestaurant = restaurantService.restaurantTotal();
        return totaldeRestaurant;
    }

//    @Autowired
//    RestaurantService restaurantService;
//
//    @Autowired
//    RestaurantMapper restaurantMapper;
//
//    @GetMapping
//    public List<RestaurantDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page,
//                                       @RequestParam(defaultValue = "10", required= false ) Integer size) throws InvalidRequestException {
//        final List<Restaurant> restaurants = restaurantService.findAll(PageRequest.of(page, size));
//        return restaurantMapper.modelToDto(restaurants);
//    }
//

}
