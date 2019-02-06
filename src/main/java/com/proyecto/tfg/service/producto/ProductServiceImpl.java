package com.proyecto.tfg.service.producto;

import com.proyecto.tfg.dao.ProductDAO;
import com.proyecto.tfg.dao.RestaurantDAO;
import com.proyecto.tfg.dto.product.ProductDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.service.AbstractService;
import com.proyecto.tfg.service.restaurant.RestaurantService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl extends AbstractService<Product, ProductDAO> implements ProductService{

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
    public List<Product> getAll(Long idRestaurant)throws NotFoundException {
        final Restaurant restaurant = restaurantService.getRestaurant(idRestaurant);
        return restaurant.getProduct();
    }

    @Override
    public Optional<Product> getById(Long idProduct) throws NotFoundException {
        return productRespository.findById(idProduct);
    }

    @Override
    public Long productTotal() {
        return productRespository.productTotales();
    }

    @Override
    public void addtorestaurant(Long idRestaurant, ProductDTO productDTO) throws NotFoundException {
        final Restaurant restaurant = restaurantService.getRestaurant(idRestaurant);
        final Product product = productService.create(productDTO);
        restaurantService.addtoproduct(restaurant,product);
    }


    @Override
    public Product getAndCheck(Long id) throws NotFoundException {
        return findById(id).orElseThrow(() -> new NotFoundException("El producto no existe"));
    }

    @Override
    public boolean isEqual(Product u1, Product u2) {
        return StringUtils.equals(u1.getName(), u2.getName()) &&
                StringUtils.equals(u1.getDescription(), u2.getDescription());
    }

    @Override
    public void setValues(Product to, Product from) {
        to.setDescription(from.getDescription());
        to.setName(from.getName());
        to.setIdProduct(from.getIdProduct());
    }

}
