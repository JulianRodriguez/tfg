package com.proyecto.tfg.controller;

import com.proyecto.tfg.component.mapper.product.ProductMapper;
import com.proyecto.tfg.dto.ApiErrorDTO;
import com.proyecto.tfg.dto.product.ProductDTO;
import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.InvalidRequestException;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.service.producto.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant/{idRestaurant}/product")
public class RestaurantProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    @PostMapping
    public void create(@RequestBody ProductDTO productDTO,
                       @PathVariable("idRestaurant") Long idRestaurant )throws NotFoundException {
        productService.addtorestaurant(idRestaurant,productDTO);
    }

//    @GetMapping
//    public List<Product> getAll(@PathVariable("idRestaurant") Long idRestaurant)throws NotFoundException {
//        return productService.getAll(idRestaurant);
//    }

    @GetMapping
    @ApiOperation(notes="Devuelve una lista de usuarios paginado, cada página tendrá un tamaño máximo de 10", tags= { "Restaurant" }, value="All restaurant")
    @ApiResponses({ @ApiResponse(code = 200, response= RestaurantDTO.class, message="All restaurant"),
            @ApiResponse(code = 401, response= ApiErrorDTO.class, message="Invalid Request")
    })
    public List<ProductDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page,
                                       @RequestParam(defaultValue = "10", required= false ) Integer size,
                                       @RequestParam(required = false) String searchName,
                                       @PathVariable("idRestaurant") Long idRestaurant) throws InvalidRequestException, NotFoundException {
        final List<Product> productList;
        if(searchName == null) {
            productList = productService.getAll(idRestaurant);
        }
        else{
            productList = productService.findProductbyiduser(idRestaurant,searchName, PageRequest.of(page, size));
        }
        final Long totalProducts = productService.productTotal();
        return productMapper.modelToDto(productList);
    }

    @GetMapping("/{idProduct}")
    public Optional<Product> getById(@PathVariable("idProduct") Long idProduct)throws NotFoundException{
        return productService.getById(idProduct);
    }


}
