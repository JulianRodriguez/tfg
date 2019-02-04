package com.proyecto.tfg.controller;

import com.proyecto.tfg.dto.product.ProductDTO;
import com.proyecto.tfg.model.Product;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "/product")
public class ProductController extends AbstractController<Product, ProductDTO> {
}
