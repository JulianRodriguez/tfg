package com.proyecto.tfg.component.mapper.restaurant;


import com.proyecto.tfg.component.mapper.Mapper;
import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Restaurant;

public interface RestaurantMapper extends Mapper<Restaurant, RestaurantDTO> {

    Restaurant dtoToModel(RestaurantDTO dto) throws NotFoundException;
}
