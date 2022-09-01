package com.proyecto.tfg.controller;

import com.proyecto.tfg.component.mapper.restaurant.RestaurantMapper;
import com.proyecto.tfg.dto.ApiErrorDTO;
import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.dto.user.UserDTO;
import com.proyecto.tfg.exception.InvalidRequestException;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.service.restaurant.RestaurantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/restaurant")
public class RestaurantController extends AbstractController<Restaurant, RestaurantDTO>{

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    RestaurantMapper restaurantMapper;

    @GetMapping("/total")
    public Long findTotal() throws NotFoundException {
        final Long totaldeRestaurant = restaurantService.restaurantTotal();
        return totaldeRestaurant;
    }

    @GetMapping("/searchTotal")
    public Long findSearchTotal(@RequestParam(required = false) String searchName) throws NotFoundException {
        final Long totaldeRestaurantSearch = restaurantService.restaurantSearchTotal(searchName);
        return totaldeRestaurantSearch;
    }

    @GetMapping
    @ApiOperation(notes="Devuelve una lista de usuarios paginado, cada página tendrá un tamaño máximo de 10", tags= { "Restaurant" }, value="All restaurant")
    @ApiResponses({ @ApiResponse(code = 200, response= RestaurantDTO.class, message="All restaurant"),
            @ApiResponse(code = 401, response= ApiErrorDTO.class, message="Invalid Request")
    })
    public List<RestaurantDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page,
                                 @RequestParam(defaultValue = "10", required= false ) Integer size,
                                 @RequestParam(required = false) String searchName) throws InvalidRequestException, NotFoundException {
        final List<Restaurant> restaurants;
        if(searchName == null) {
            restaurants = restaurantService.findAll(PageRequest.of(page, size));
        }
        else{
            restaurants = restaurantService.findByName(searchName, PageRequest.of(page, size));
        }
        final Long totalRestaurant = restaurantService.restaurantTotal();
        return restaurantMapper.modelToDto(restaurants);
    }

    @PutMapping("/{idRestaurant}")
    public void update(@PathVariable("idRestaurant") Long id, @RequestBody RestaurantDTO dto) throws InvalidRequestException, NotFoundException {
        if(dto.getIdRestaurant() != null)
            throw new InvalidRequestException("El idRestaurante no se puede recibir en el body");
        final Restaurant restaurant = restaurantService.getAndCheck(id);
        System.out.println("Imprimo el dto");
        System.out.println(dto);
        final Restaurant restaurantFrom = restaurantMapper.dtoToModel(dto);
        final Restaurant restaurantTo = restaurantService.updateValores(restaurant, restaurantFrom);
        restaurantService.update(restaurantTo);
    }

    @GetMapping("/{idRestaurant}/product/total")
    public Long findTotal(@PathVariable("idRestaurant") Long id) throws NotFoundException {
        final Long totaldeProduct = restaurantService.productTotalRestaurant(id);
        return totaldeProduct;
    }


}
