package com.proyecto.tfg.service.user;

import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.model.User;
import com.proyecto.tfg.service.Service;
import org.springframework.data.domain.Pageable;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService extends Service<User, Long> {
	
	User getAndCheckByUsername(String username) throws NotFoundException;
	void addrestaurant(Long idUser, Restaurant r)throws NotFoundException;
	User getUser(Long idUser) throws NotFoundException;
	User getUserWithEmail(String email) throws NotFoundException;
	void removeRestaurant(User u, Restaurant r);
	Long usertotal();
	Long userSearchTotal(String name);
	List<User> findByName(String name, Pageable p) throws NotFoundException;
	User updateValores(User u, User y);
	Boolean CheckByUsername(String username) throws NotFoundException;
	Boolean CheckByEmail(String email) throws NotFoundException;
	Boolean checkBypass(String email, String pass) throws NotFoundException, UnsupportedEncodingException;



}
