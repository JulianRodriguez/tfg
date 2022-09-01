package com.proyecto.tfg.service.restaurant;


import com.proyecto.tfg.dao.ProductDAO;
import com.proyecto.tfg.dao.RestaurantDAO;
import com.proyecto.tfg.dao.UserDAO;
import com.proyecto.tfg.dto.restaurant.RestaurantDTO;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Product;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.model.User;
import com.proyecto.tfg.service.AbstractService;
import com.proyecto.tfg.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class RestaurantServiceImpl extends AbstractService<Restaurant, RestaurantDAO> implements RestaurantService {

    @Autowired
    private RestaurantDAO restaurantRepository;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    @Override
    public Restaurant getRestaurant(Long idRest) throws NotFoundException {
        return restaurantRepository.findById(idRest)
                .orElseThrow(()->new NotFoundException(String.format("Restaurante no encontrado (%d)",idRest)));

    }

//    @Override
//    public List<Restaurant> getAll() {
//
//        return restaurantRepository.findAll();
//    }

    @Override
    public Restaurant create(RestaurantDTO Restdto) {

        Restaurant restaurantCrear = new Restaurant();
        restaurantCrear.setNameRestaurant(Restdto.getNameRestaurant());
        restaurantCrear.setDescriptionRestaurant(Restdto.getDescriptionRestaurant());
        return restaurantRepository.save(restaurantCrear);
    }

    @Override
    public void addtouser(Long idUser, RestaurantDTO restaurantDTO) throws NotFoundException{
        final Restaurant newRestaurant = restaurantService.create(restaurantDTO);
        userService.addrestaurant(idUser,newRestaurant);
//        return newRestaurant;
    }

    @Override
    public void deleteRestaurant(Long idUser, Long idRestaurant) throws NotFoundException {

        final User u = userService.getUser(idUser);
        final Restaurant r = restaurantService.getRestaurant(idRestaurant);
        userService.removeRestaurant(u,r);
    }

    @Override
    public void addtoproduct(Long idRestaurant, Product product) throws NotFoundException {
        Restaurant r = getRestaurant(idRestaurant);
        r.getProduct().add(product);
        restaurantRepository.save(r);
        product.setRestaurant(r);
        productDAO.save(product);
    }

    @Override
    public List<Restaurant> findRestaurantbyiduser(Long id, Pageable p) throws NotFoundException {
        User u = userService.getAndCheck(id);
        return userDAO.findRestaurantbyiduser(id, PageRequest.of(p.getPageNumber(), p.getPageSize()));
    }

    @Override
    public Long restaurantTotal() {
        return restaurantRepository.restaurantTotal();
    }

    @Override
    public Long restaurantSearchTotal(String name) {
        return restaurantRepository.restaurantSearchTotal(name);
    }

    @Override
    public List<Restaurant> findByName(String name, Pageable p) throws NotFoundException {
        return restaurantRepository.findByName(name.toLowerCase(Locale.getDefault()), p);
    }

    @Override
    public List<Restaurant> findIdUserAndByName(Long id, String name, Pageable p) throws NotFoundException {
        return userDAO.findRestauratbyiduserandname(id,name.toLowerCase(Locale.getDefault()), p);
    }

    @Override
    public Restaurant updateValores(Restaurant u, Restaurant y) {

        if(y.getNameRestaurant()!=null)
            u.setNameRestaurant(y.getNameRestaurant());
        if (y.getDescriptionRestaurant()!=null)
            u.setDescriptionRestaurant(y.getDescriptionRestaurant());
        return u;

    }

    @Override
    public Restaurant getAndCheck(Long id) throws NotFoundException {
        return findById(id).orElseThrow(() -> new NotFoundException("El usuario no existe"));
    }

    @Override
    public boolean isEqual(Restaurant u1, Restaurant u2) {
        return StringUtils.equals(u1.getDescriptionRestaurant(), u2.getDescriptionRestaurant()) &&
                StringUtils.equals(u1.getNameRestaurant(), u2.getNameRestaurant());
    }

    @Override
    public void setValues(Restaurant to, Restaurant from) {
        to.setDescriptionRestaurant(from.getDescriptionRestaurant());
        to.setNameRestaurant(from.getNameRestaurant());
        to.setProduct(from.getProduct());

    }

    @Override
    public Long productTotalRestaurant(Long idRestaurant) {
        return restaurantRepository.productTotales(idRestaurant);
    }
}
