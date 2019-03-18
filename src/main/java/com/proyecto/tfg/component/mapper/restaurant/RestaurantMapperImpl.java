package com.proyecto.tfg.component.mapper.restaurant;

import com.proyecto.tfg.component.mapper.AbstractMapper;
import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.model.User;
import com.proyecto.tfg.service.restaurant.RestaurantService;
import com.proyecto.tfg.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantMapperImpl extends AbstractMapper<Restaurant, RestaurantDTO> implements RestaurantMapper {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;


    @Override
    public Class<? extends RestaurantDTO> dtoClazz() {
        return RestaurantDTO.class;
    }

    @Override
    public Class<? extends Restaurant> modelClazz() {
        return Restaurant.class;
    }

    private User longToUser(Long idUser) throws NotFoundException {
        return userService.findById(idUser)
                .orElseThrow(() -> new NotFoundException("Usuario no existe"));
    }


    @Override
    public RestaurantDTO modelToDto(Restaurant model) {
        RestaurantDTO dto = dozer.map(model, dtoClazz());
        return dto;
    }
}