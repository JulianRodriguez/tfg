package com.proyecto.tfg.dao;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.proyecto.tfg.model.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.tfg.model.User;

@Repository
public interface UserDAO extends GenericDAO<User> {

	Optional<User> findByUsername(String username);

	@Query(value = "select count(username) from User where username = :username")
	Long BuscarPorUsername(String username);

	@Query(value = "select count(email) from User where email = :email AND password = :pass")
	Long BuscarPorPass(String email, String pass);

	@Query(value = "select count(email) from User where email = :email")
	Long BuscarPorEmail(String email);

	User findByEmail(String email);

	@Query(value = "select q from User as r join r.restaurant as q where r.idUser = :idUser")
	List<Restaurant> findRestaurantbyiduser(@Param("idUser") Long idUser, Pageable p);

	@Query(value = "select r from User as q join q.restaurant as r where q.idUser = :idUser and LOWER(r.nameRestaurant) LIKE %:name%")
	List<Restaurant> findRestauratbyiduserandname(@Param("idUser") Long idUser,@Param("name") String name, Pageable p);

	@Query(value = "select count(idUser) from User")
	Long userTotales();



	@Query(value = "select count(u) from User AS u where LOWER(u.name) LIKE %:name%")
	Long userTotalesSearch(@Param("name") String name);

	@Query(value = "select u from User AS u where LOWER(u.name) LIKE %:name%")
	List<User> findByName (  String name, Pageable pageable);
}
