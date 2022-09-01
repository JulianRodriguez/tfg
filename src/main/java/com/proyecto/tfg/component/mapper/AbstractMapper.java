package com.proyecto.tfg.component.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.proyecto.tfg.exception.NotFoundException;

public abstract class AbstractMapper<T, E> implements Mapper<T, E> {
	
	@Autowired
	public DozerBeanMapper dozer;
	
	@Override
	public T dtoToModel(E dto) throws NotFoundException {
		return dozer.map(dto, modelClazz());
	}

	@Override
	public E modelToDto(T model) {
		return dozer.map(model, dtoClazz());
	}

	@Override
	public List<T> dtoToModel(List<E> dtos) throws NotFoundException {
		List<T> models = new ArrayList<>();
		if(dtos != null) 
			for(E dto : dtos)
				models.add(dtoToModel(dto));
		return models;
	}

	@Override
	public List<E> modelToDto(List<T> models) {
		return models.stream().map(this::modelToDto).collect(Collectors.toList());
	}
}
