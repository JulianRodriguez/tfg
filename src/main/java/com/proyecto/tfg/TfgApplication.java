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

			//Puede hacer todo
			Role r1 = new Role();
			r1.setName("ADMIN");
			List<Privilege> apr1 = new ArrayList<>();


			//podrá crud de sus platos
			Role r2 = new Role();
			r2.setName("RESTAURANT");
			List<Privilege> apr2 = new ArrayList<>();

			//Un usuario solo podra leer un plato
			Role r3 = new Role();
			r3.setName("USER");
			List<Privilege> apr3 = new ArrayList<>();
			
			//Privileges
			Privilege p1 = new Privilege();
			p1.setName("GET_RESTAURANT");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Privilege();
			p1.setName("POST_RESTAURANT");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Privilege();
			p1.setName("PUT_RESTAURANT");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Privilege();
			p1.setName("DELETE_RESTAURANT");
			privilegeDAO.save(p1);
			apr1.add(p1);
			
			p1 = new Privilege();
			p1.setName("GET_USER");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Privilege();
			p1.setName("POST_USER");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Privilege();
			p1.setName("PUT_USER");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Privilege();
			p1.setName("DELETE_USER");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			
			p1 = new Privilege();
			p1.setName("GET_PRIVILEGE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Privilege();
			p1.setName("POST_PRIVILEGE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Privilege();
			p1.setName("PUT_PRIVILEGE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Privilege();
			p1.setName("DELETE_PRIVILEGE");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Privilege();
			p1.setName("GET_ROLE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Privilege();
			p1.setName("POST_ROLE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Privilege();
			p1.setName("PUT_ROLE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Privilege();
			p1.setName("DELETE_ROLE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			
			r1.setPrivilege(apr1);
			roleDAO.save(r1);
			
			r2.setPrivilege(apr2);
			roleDAO.save(r2);

			//Users
			User admin = new User();
//			User user = new User();





			List<Restaurant> restaurants = new ArrayList<>();
			Restaurant restaurant2S = new Restaurant();
			restaurant2S.setNameRestaurant("McDonald");
			restaurant2S.setDescriptionRestaurant("Hambu");
			restaurants.add(restaurant2S);
			Restaurant restaurant = new Restaurant();
			restaurant.setNameRestaurant("La Pepa");
			restaurant.setDescriptionRestaurant("Cerveza artesanal");
			restaurants.add(restaurant);

			List<Restaurant> restaurants2 = new ArrayList<>();

			Restaurant restaurant2J = new Restaurant();
			restaurant2J.setNameRestaurant("BurgerKing");
			restaurant2J.setDescriptionRestaurant("Cositas bonitas");
			restaurants2.add(restaurant2J);
			Restaurant restaurant2 = new Restaurant();
			restaurant2.setNameRestaurant("Rincon de Ana");
			restaurant2.setDescriptionRestaurant("Comida artesanal");
			restaurants2.add(restaurant2);
			System.out.println(restaurants);


			Product product = new Product();
            Product product2 = new Product();
			Product product3 = new Product();
			Product product4 = new Product();
            List<Product> prod1 = new ArrayList<>();
            List<Product> prod2 = new ArrayList<>();
            product.setDescription("Plato de arroz muy rico. Hecho en cai");
            product.setName("Paella");
            product2.setName("Hamburguesa de queso");
            product2.setDescription("Famosa hamburguesa tipica de aqui");
			product3.setName("Carrillada");
			product3.setDescription("carnecita");
			product4.setName("Salchicha");
			product4.setDescription("bbb");
            prod1.add(product);
			prod1.add(product4);
			prod1.add(product3);

            prod2.add(product2);

            restaurant.setProduct(prod1);
            restaurant2.setProduct(prod2);

            admin.setName("Julian Rodriguez Carave");
			admin.setUsername("julito");
			admin.setEmail("j@gmail.com");
			admin.setPhone("667884995");
			admin.setPassword(DigestUtils.sha1Hex("1234"));
			admin.setRole(r1);
			admin.setRestaurant(restaurants2);
			userDAO.save(admin);

			User user = new User();
			user.setName("Sara Zar");
			user.setUsername("sarita04");
			user.setEmail("sar@gmail.com");
			user.setPhone("435243124");
			user.setPassword(DigestUtils.sha1Hex("1234"));
			user.setRole(r2);
			user.setRestaurant(restaurants);
			userDAO.save(user);

			for(int i = 0; i<100 ; i++)
			{
				User userprueba = new User();
				userprueba.setName("Paco"+i);
				userprueba.setUsername("Paco"+i);
				userprueba.setEmail("Paco@gmail.com"+i);
				userprueba.setPhone("435243124"+i);
				userprueba.setPassword(DigestUtils.sha1Hex("1234"+i));
				userprueba.setRole(r2);
				userDAO.save(userprueba);
			}







		};
	}
}
