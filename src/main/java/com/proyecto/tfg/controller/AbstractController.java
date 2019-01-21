package com.proyecto.tfg.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.tfg.component.mapper.Mapper;
import com.proyecto.tfg.exception.InvalidRequestException;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.service.Service;

public abstract class AbstractController<T, E> {

	@Autowired
	Service<T, Long> service;
	
	@Autowired
	Mapper<T, E> mapper;
	
	@GetMapping
	public List<E> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page, 
							 @RequestParam(defaultValue = "10", required= false ) Integer size) throws InvalidRequestException {
		final List<T> models = service.findAll(PageRequest.of(page, size));
		return mapper.modelToDto(models);
	}
	
	@GetMapping("/{id}")
	public E findById(@PathVariable("id") Long id) throws NotFoundException {
		final T model = service.getAndCheck(id);
		return mapper.modelToDto(model);
	}
	
	@PostMapping
	public E create(HttpServletRequest request, @RequestBody E dto) throws InvalidRequestException, NotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Method method = mapper.dtoClazz().getMethod("getId" + mapper.dtoClazz());
		if(method.invoke(dto) != null)
			throw new InvalidRequestException("El id no se puede recibir en el body");
		final T user = mapper.dtoToModel(dto);
		final T createUser = service.create(user);
		return mapper.modelToDto(createUser);
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable("id") Long id, @RequestBody E dto) throws InvalidRequestException, NotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Method method = mapper.dtoClazz().getMethod("getId" + mapper.dtoClazz());
		if(method.invoke(dto) != null) 
			throw new InvalidRequestException("El id no se puede recibir en el body");
		final T model = service.getAndCheck(id);
		service.setValues(model, mapper.dtoToModel(dto));
		service.update(model);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id, @RequestBody E dto) throws InvalidRequestException, NotFoundException {
		final T model = service.getAndCheck(id);
		if(!service.isEqual(mapper.dtoToModel(dto), model)) 
			throw new InvalidRequestException("El " + mapper.modelClazz() + " recibido no coincide con el almacenado");
		service.delete(model);
	}
}
