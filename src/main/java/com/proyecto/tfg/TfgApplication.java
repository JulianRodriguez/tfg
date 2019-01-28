package com.proyecto.tfg;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.tfg.dao.PrivilegeDAO;
import com.proyecto.tfg.dao.RestaurantDAO;
import com.proyecto.tfg.dao.RoleDAO;
import com.proyecto.tfg.dao.UserDAO;
import com.proyecto.tfg.model.Privilege;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.model.Role;
import com.proyecto.tfg.model.User;
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
			p1 = new Privilege();
			p1.setName("POST_USER");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Privilege();
			p1.setName("PUT_USER");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Privilege();
			p1.setName("DELETE_USER");
			privilegeDAO.save(p1);
			apr1.add(p1);
			
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
			User user = new User();

			Restaurant restaurant = new Restaurant();
			List<Restaurant> restaurants = new ArrayList<>();
			restaurant.setNameRestaurant("La Pepa");
			restaurant.setDescriptionRestaurant("Cerveza artesanal");
//			restaurantDAO.save(restaurant);
			restaurants.add(restaurant);
			System.out.println(restaurants);

			admin.setName("Julian Rodriguez Carave");
			admin.setUsername("julito");
			admin.setEmail("j@gmail.com");
			admin.setPhone("667884995");
			admin.setPassword(DigestUtils.sha1Hex("1234"));
			admin.setRole(r1);
			userDAO.save(admin);

			user.setName("Sara Zar");
			user.setUsername("sarita04");
			user.setEmail("sar@gmail.com");
			user.setPhone("435243124");
			user.setPassword(DigestUtils.sha1Hex("1234"));
			user.setRole(r2);
			user.setRestaurant(restaurants);
			userDAO.save(user);



		};
	}
}
