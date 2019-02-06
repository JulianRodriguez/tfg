package com.proyecto.tfg.controller;

import com.proyecto.tfg.dto.product.ProductDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import com.proyecto.tfg.service.producto.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "/product")
public class ProductController extends AbstractController<Product, ProductDTO> {

    @Autowired
    ProductService productService;

    @GetMapping("/total")
    public Long findTotal() throws NotFoundException {
        final Long totaldeProduct = productService.productTotal();
        return totaldeProduct;
    }
}
