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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ProductServiceImpl extends AbstractService<Product, ProductDAO> implements ProductService{

    @Autowired
    ProductService productService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    private ProductDAO productRespository;

    @Autowired
    RestaurantDAO restaurantDAO;

    @Override
    public Product create(ProductDTO productDTO, Restaurant restaurant) {
        final Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPhoto(productDTO.getPhoto());
        product.setRestaurant(restaurant);
        return productRespository.save(product);
    }

    @Override
    public List<Product> getAll(Long idRestaurant)throws NotFoundException {
        final Restaurant restaurant = restaurantService.getRestaurant(idRestaurant);
        return restaurant.getProduct();
    }

    @Override
    public List<Product> findProductbyiduser(Long id,String name, Pageable p) throws NotFoundException {
        final Restaurant restaurant = restaurantService.getAndCheck(id);
        return restaurantDAO.findProductbyiduserandName(id,name, PageRequest.of(p.getPageNumber(), p.getPageSize()));
    }

    @Override
    public Product updateValores(Product u, Product y) {
        if(y.getName() != null)
            u.setName(y.getName());
        if(y.getDescription() != null)
            u.setDescription(y.getDescription());
        if(y.getPhoto() != null)
            u.setPhoto(y.getPhoto());
        return u;

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
        final Product product = productService.create(productDTO,restaurant);
        restaurantService.addtoproduct(restaurant,product);
    }


    @Override
    public Product getProduct(Long id) throws NotFoundException {
        try{
            final Product product2 = productRespository.findByIdProduct(id);

            return product2;
        }catch (Exception e){
            throw new NotFoundException("El producto no existe");
        }
    }

    @Override
    public Product getAndCheck(Long id) throws NotFoundException {
        return null;
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
        to.setPhoto(from.getPhoto());
    }

    @Override
    public List<Product> findByName(String name, Pageable p) throws NotFoundException {
        return productRespository.findByName(name.toLowerCase(Locale.getDefault()), p);
    }

    @Override
    public Long productSearchTotal(String name) {
        return productRespository.productSearchTotal(name);
    }



}
