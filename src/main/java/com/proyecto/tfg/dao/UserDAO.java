package com.proyecto.tfg.dao;

import java.util.List;
import java.util.Optional;

import com.proyecto.tfg.model.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.tfg.model.User;

@Repository
public interface UserDAO extends GenericDAO<User> {

	Optional<User> findOneByUsername(String username);
//	List<Restaurant> fin

	@Query(value = "select q from User as r join r.restaurant as q where r.idUser = :idUser")
	List<Restaurant> findRestaurantbyiduser(@Param("idUser") Long idUser, Pageable p);

	@Query(value = "select count(idUser) from User")
	Long userTotales();
}
