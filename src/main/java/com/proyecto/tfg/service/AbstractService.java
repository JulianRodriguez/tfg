package com.proyecto.tfg.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.proyecto.tfg.dao.GenericDAO;
import com.proyecto.tfg.exception.InvalidRequestException;

public abstract class AbstractService<T, D extends GenericDAO<T>> implements Service<T, Long> {

	private static final Integer MAXSIZE = Integer.valueOf(10);
	
	@Autowired
	protected D dao;
	
	@Override
	public T create(T t) {
		return dao.save(t);
	}

	@Override
	public void update(T t) {
		dao.save(t);
	}

	@Override
	public Optional<T> findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<T> findAll(Pageable p) throws InvalidRequestException {
		if(p.getPageNumber() < 0 || p.getPageSize() <= 0 || p.getPageSize() > MAXSIZE)
			throw new InvalidRequestException("Los parámetros introducidos contienen valores no permitidos, page mayor o igual a 0 y size entre 1 y " + MAXSIZE + " incluidos");
		return dao.findAll(p).stream().collect(Collectors.toList());
	}

	@Override
	public void delete(T t) {
		dao.delete(t);
	}
}
