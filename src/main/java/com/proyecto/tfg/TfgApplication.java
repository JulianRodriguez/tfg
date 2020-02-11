package com.proyecto.tfg;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.tfg.dao.PrivilegeDAO;
import com.proyecto.tfg.dao.RestaurantDAO;
import com.proyecto.tfg.dao.RoleDAO;
import com.proyecto.tfg.dao.UserDAO;
import com.proyecto.tfg.model.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TfgApplication {

    public static void main(String[] args) {
        SpringApplication.run(TfgApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PrivilegeDAO privilegeDAO, RoleDAO roleDAO,
                                  UserDAO userDAO, RestaurantDAO restaurantDAO) {
        return args -> {

            //ADMINISTRADOR DE LA APLICACION
            Role r1 = new Role();
            r1.setName("ADMIN");
            List<Privilege> apr1 = new ArrayList<>();

			//RESTAURANTE REGISTRADO EN LA APP
			Role r2 = new Role();
			r2.setName("RESTAURANT");
			List<Privilege> apr2 = new ArrayList<>();


			//USER SOLO PUEDE VER EN LA APP
			Role r3 = new Role();
			r3.setName("USER");
			List<Privilege> apr3 = new ArrayList<>();

			Privilege privilege = new Privilege();

            //ROLE
            privilege.setName("GET_ROLE");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
            privilege = new Privilege();
            privilege.setName("POST_ROLE");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
            privilege = new Privilege();
            privilege.setName("PUT_ROLE");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
            privilege = new Privilege();
            privilege.setName("DELETE_ROLE");
            privilegeDAO.save(privilege);

            //PRIVILEGE
            privilege = new Privilege();
            privilege.setName("GET_PRIVILEGE");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
            privilege = new Privilege();
            privilege.setName("POST_PRIVILEGE");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
            privilege = new Privilege();
            privilege.setName("PUT_PRIVILEGE");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
            privilege = new Privilege();
            privilege.setName("DELETE_PRIVILEGE");
            privilegeDAO.save(privilege);
            apr1.add(privilege);

            //PRODUCTOS DE UN RESTAURANTE
            privilege = new Privilege();
            privilege.setName("GET_PRODUCT_RESTAURANT");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
            apr2.add(privilege);
			apr3.add(privilege);
            privilege = new Privilege();
            privilege.setName("POST_PRODUCT_RESTAURANT");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
			apr2.add(privilege);

            //RESTAURANTES DE USUARIO
            privilege = new Privilege();
            privilege.setName("GET_RESTAURANT_USER");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
			apr2.add(privilege);
			apr3.add(privilege);
            privilege = new Privilege();
            privilege.setName("DELETE_RESTAURANT_USER");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
			apr2.add(privilege);
            privilege = new Privilege();
            privilege.setName("POST_RESTAURANT_USER");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
			apr2.add(privilege);

            //USER
            privilege = new Privilege();
            privilege.setName("GET_USER");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
			apr2.add(privilege);
			apr3.add(privilege);
            privilege = new Privilege();
            privilege.setName("POST_USER");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
			privilege = new Privilege();
			privilege.setName("PUT_USER");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
			apr2.add(privilege);
			privilege = new Privilege();
			privilege.setName("DELETE_USER");
            privilegeDAO.save(privilege);
            apr1.add(privilege);

            //EMAIL
			privilege = new Privilege();
			privilege.setName("PUT_EMAIL");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
			apr2.add(privilege);

            //PRODUCT
			privilege = new Privilege();
			privilege.setName("GET_PRODUCT");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
			apr2.add(privilege);
			apr3.add(privilege);

            //RESTAURANT
            privilege = new Privilege();
			privilege.setName("GET_RESTAURANT");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
			apr2.add(privilege);
			apr3.add(privilege);
			privilege = new Privilege();
			privilege.setName("POST_RESTAURANT");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
			apr2.add(privilege);
			privilege = new Privilege();
			privilege.setName("PUT_RESTAURANT");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
			apr2.add(privilege);
			privilege = new Privilege();
			privilege.setName("DELETE_RESTAURANT");
            privilegeDAO.save(privilege);
            apr1.add(privilege);
			apr2.add(privilege);


            r1.setPrivilege(apr1);
            roleDAO.save(r1);

            r2.setPrivilege(apr1);
            roleDAO.save(r2);

            r3.setPrivilege(apr3);
            roleDAO.save(r3);



            //CREAMOS Productos

			Product product = new Product();
			product.setDescription("Plato de arroz muy rico. Hecho en cai");
			product.setName("Paella Mixta");


			Product product2 = new Product();
			product2.setName("Hamburguesa de queso");
			product2.setDescription("Famosa hamburguesa tipica de aqui");

			Product product3 = new Product();
			product3.setName("Carrillada");
			product3.setDescription("carnecita muy rica");

			Product product4 = new Product();
			product4.setName("Salchicha");
			product4.setDescription("bbb");

			List<Product> prod1 = new ArrayList<>();
			List<Product> prod2 = new ArrayList<>();
			List<Product> prod3 = new ArrayList<>();

			prod1.add(product);
			prod2.add(product2);
			prod2.add(product3);
			prod3.add(product4);


			//CREAMOS RESTAURANTE

			List<Restaurant> restaurants = new ArrayList<>();
			Restaurant restaurant = new Restaurant();
			restaurant.setNameRestaurant("McDonald");
			restaurant.setDescriptionRestaurant("Comida rápida Americana");
			restaurant.setProduct(prod1);
			restaurants.add(restaurant);

			product.setRestaurant(restaurant);

			List<Restaurant> restaurants2 = new ArrayList<>();
			Restaurant restaurant2 = new Restaurant();
            restaurant2.setNameRestaurant("La Pepa");
            restaurant2.setDescriptionRestaurant("Cerveza artesanal y tapas variadas");
			restaurant2.setProduct(prod2);
			restaurants2.add(restaurant2);

            product2.setRestaurant(restaurant2);
            product3.setRestaurant(restaurant2);

			Restaurant restaurant3 = new Restaurant();
			restaurant3.setNameRestaurant("BurgerKing");
			restaurant3.setDescriptionRestaurant("Comida rápida copia de McDonald");
			restaurant3.setProduct(prod3);
			restaurants2.add(restaurant3);

            product4.setRestaurant(restaurant3);


			//CREAMOS USUARIOS


            User admin = new User();

            admin.setName("Julian Rodriguez Carave");
            admin.setUsername("julito");
            admin.setEmail("j@gmail.com");
            admin.setPhone("667884995");
            admin.setPassword(DigestUtils.sha1Hex("123456"));
            admin.setRole(r1);
            admin.setRestaurant(restaurants);
            userDAO.save(admin);

            User userRestaurant = new User();
			userRestaurant.setName("Sara Zaruela");
			userRestaurant.setUsername("sarita04");
			userRestaurant.setEmail("sar@gmail.com");
			userRestaurant.setPhone("435243124");
			userRestaurant.setPassword(DigestUtils.sha1Hex("123456"));
			userRestaurant.setRole(r2);
			userRestaurant.setRestaurant(restaurants2);
            userDAO.save(userRestaurant);

            for (int i = 0; i < 100; i++) {
                User userprueba = new User();
                userprueba.setName("Paco" + i);
                userprueba.setUsername("Pacome" + i);
                userprueba.setEmail("Pacome" + i +"@gmail.com");
                userprueba.setPhone("435243124" + i);
                userprueba.setPassword(DigestUtils.sha1Hex("12345" + i));
                userprueba.setRole(r3);
                userDAO.save(userprueba);
            }


        };
    }
}
