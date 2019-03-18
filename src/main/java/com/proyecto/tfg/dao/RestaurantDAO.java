package com.proyecto.tfg.dao;

import com.proyecto.tfg.model.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantDAO extends GenericDAO<Restaurant> {

    @Query(value = "select count(idRestaurant) from Restaurant ")
    Long restaurantTotal();

    @Query(value = "select count(u) from Restaurant AS u where LOWER(u.nameRestaurant) LIKE %:name%")
    Long restaurantSearchTotal(@Param("name") String name);

    @Query(value = "select u from Restaurant AS u where LOWER(u.nameRestaurant) LIKE %:name%")
    List<Restaurant> findByName (@Param("name") String name, Pageable pageable);

}

