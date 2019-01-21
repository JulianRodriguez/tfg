package com.proyecto.tfg.controller;

import com.proyecto.tfg.dto.product.ProductDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
import com.proyecto.tfg.service.producto.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant/{idRestaurant}/product")
public class RestaurantProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public void create(@RequestBody ProductDTO productDTO,
                       @PathVariable("idRestaurant") Integer idRestaurant )throws NotFoundException {
        productService.addtorestaurant(idRestaurant,productDTO);
    }

    @GetMapping
    public List<Product> getAll(@PathVariable("idRestaurant") Integer idRestaurant)throws NotFoundException {
        return productService.getAll(idRestaurant);
    }

    @GetMapping("/{idProduct}")
    public Optional<Product> getById(@PathVariable("idProduct") Integer idProduct)throws NotFoundException{
        return productService.getById(idProduct);
    }


}
