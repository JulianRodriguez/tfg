package com.proyecto.tfg.component.mapper;

import java.util.List;

import com.proyecto.tfg.exception.NotFoundException;

public interface Mapper<T, E> {

	T dtoToModel(E dto) throws NotFoundException;
	E modelToDto(T model);
	List<T> dtoToModel(List<E> dtos) throws NotFoundException;
	List<E> modelToDto(List<T> models);
	Class<? extends E> dtoClazz();
	Class<? extends T> modelClazz();
}
