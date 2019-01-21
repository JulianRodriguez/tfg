package com.proyecto.tfg.service.producto;

import com.proyecto.tfg.dao.ProductDAO;
import com.proyecto.tfg.dto.product.ProductDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductService productService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    private ProductDAO productRespository;

    @Override
    public Product create(ProductDTO productDTO) {
        final Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        return productRespository.save(product);
    }

    @Override
    public List<Product> getAll(Integer idRestaurant)throws NotFoundException {
        final Restaurant restaurant = restaurantService.getRestaurant(idRestaurant);
        return restaurant.getProduct();
    }

    @Override
    public Optional<Product> getById(Integer idProduct) throws NotFoundException {
        return productRespository.findById(idProduct);
    }

    @Override
    public void addtorestaurant(Integer idRestaurant, ProductDTO productDTO) throws NotFoundException {
        final Restaurant restaurant = restaurantService.getRestaurant(idRestaurant);
        final Product product = productService.create(productDTO);
        restaurantService.addtoproduct(restaurant,product);
    }


}
