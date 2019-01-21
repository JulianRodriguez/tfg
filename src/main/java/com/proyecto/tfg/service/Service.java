package com.proyecto.tfg.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.proyecto.tfg.exception.InvalidRequestException;
import com.proyecto.tfg.exception.NotFoundException;

public interface Service<T, I extends Serializable> {

	T create(T t);
	void update(T t);
	Optional<T> findById(I id);
	List<T> findAll(Pageable p) throws InvalidRequestException;
	void delete(T t);
	
	T getAndCheck(Long id) throws NotFoundException;
	boolean isEqual(T u1, T u2);
	void setValues(T to, T from);
}
