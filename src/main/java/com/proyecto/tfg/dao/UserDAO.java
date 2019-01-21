package com.proyecto.tfg.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.proyecto.tfg.model.User;

@Repository
public interface UserDAO extends GenericDAO<User> {

	Optional<User> findOneByUsername(String username);
}
