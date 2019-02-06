package com.proyecto.tfg.service.user;

import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.model.User;
import com.proyecto.tfg.service.Service;

import java.util.List;

public interface UserService extends Service<User, Long> {
	
	User getAndCheckByUsername(String username) throws NotFoundException;
	void addrestaurant(Long idUser, Restaurant r)throws NotFoundException;
	User getUser(Long idUser) throws NotFoundException;
	void removeRestaurant(User u, Restaurant r);
	Long usertotal();


}
