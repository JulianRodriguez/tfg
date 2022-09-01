package com.proyecto.tfg.controller;

import com.proyecto.tfg.component.mapper.product.ProductMapper;
import com.proyecto.tfg.dto.ApiErrorDTO;
import com.proyecto.tfg.dto.product.ProductDTO;
import com.proyecto.tfg.dto.product.ProductEditDTO;
import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.InvalidRequestException;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.service.producto.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value= "/product")
public class ProductController extends AbstractController<Product, ProductDTO> {

    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    @GetMapping("/total")
    public Long findTotal() throws NotFoundException {
        final Long totaldeProduct = productService.productTotal();
        return totaldeProduct;
    }

    @GetMapping
    @ApiOperation(notes="Devuelve una lista de usuarios paginado, cada página tendrá un tamaño máximo de 10", tags= { "Product" }, value="All product")
    @ApiResponses({ @ApiResponse(code = 200, response= ProductDTO.class, message="All product"),
            @ApiResponse(code = 401, response= ApiErrorDTO.class, message="Invalid Request")
    })
    public List<ProductDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page,
                                       @RequestParam(defaultValue = "10", required= false ) Integer size,
                                       @RequestParam(required = false) String searchName) throws InvalidRequestException, NotFoundException {
        final List<Product> products;
        if(searchName == null) {
            products = productService.findAll(PageRequest.of(page, size));
        }
        else{
            products = productService.findByName(searchName, PageRequest.of(page, size));
        }
        final Long totalRestaurant = productService.productTotal();
        return productMapper.modelToDto(products);
    }

    @GetMapping("/searchTotal")
    public Long findSearchTotal(@RequestParam(required = false) String searchName) throws NotFoundException {
        final Long totaldeProductsSearch = productService.productSearchTotal(searchName);
        return totaldeProductsSearch;
    }


    @PutMapping("/{idProduct}")
    public void update(@PathVariable("idProduct") Long id, @RequestBody ProductDTO dto) throws InvalidRequestException, NotFoundException {

        try{
            if(dto.getIdProduct() != null)
                throw new InvalidRequestException("El idProduct no se puede recibir en el body");
            final Product product = productService.getProduct(id);
            final Product productFrom = productMapper.dtoToModel(dto);
            final Product productTo = productService.updateValores(product, productFrom);
            productService.update(productTo);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable("id") Long id) throws NotFoundException {
        final Product model = productService.getProduct(id);
        final ProductDTO modelDto = productMapper.modelToDto(model);
        return modelDto;
    }

}
