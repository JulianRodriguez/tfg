package com.proyecto.tfg.service.user;

import com.proyecto.tfg.dao.UserDAO;
import com.proyecto.tfg.model.Restaurant;
import com.proyecto.tfg.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.service.AbstractService;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl extends AbstractService<User, UserDAO> implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public User getUser(Long idUser) throws NotFoundException {
		return userDAO.findById(idUser)
				.orElseThrow(()->new NotFoundException(String.format("Usuario no encontrado (%d)",idUser)));
	}
	
	@Override
	public User getAndCheck(Long idUser) throws NotFoundException {
		return findById(idUser).orElseThrow(() -> new NotFoundException("El usuario no existe"));
	}
	
	@Override
	public boolean isEqual(User u1, User u2) {
		System.out.println(u1.getRole().getIdRole());
		System.out.println(u2.getRole().getIdRole());
		System.out.println(u1.getRole());
		System.out.println(u2.getRole());
		return StringUtils.equals(u1.getName(), u2.getName()) &&
				StringUtils.equals(u1.getEmail(), u2.getEmail()) &&
				StringUtils.equals(u1.getPhone(), u2.getPhone()) &&
				StringUtils.equals(u1.getUsername(), u2.getUsername()) &&
				u1.getRole().getIdRole().equals(u2.getRole().getIdRole());
	}
	
	@Override
	public void setValues(User to, User from) {
		to.setName(from.getName());
		to.setEmail(from.getEmail());
		to.setPhone(from.getPhone());
		to.setUsername(from.getUsername());
		to.setPassword(from.getPassword());
		to.setRole(from.getRole());
	}

	@Override
	public void removeRestaurant(User u, Restaurant r) {
		u.getRestaurant().remove(r);
		userDAO.save(u);
	}

	@Override
	public User getAndCheckByUsername(String username) throws NotFoundException {
		return userDAO.findByUsername(username).orElseThrow(() -> new NotFoundException("El usuario no existe"));
	}

	@Override
	public Boolean CheckByUsername(String username) throws NotFoundException {

		Long i = userDAO.BuscarPorUsername(username);
		System.out.println(i);
		if(i>0){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Boolean CheckByEmail(String email) throws NotFoundException {
		boolean valido = false;
		Long i = userDAO.BuscarPorEmail(email);
		if(i>0){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public void addrestaurant(Long idUser, Restaurant r) throws NotFoundException {
		User u = getUser(idUser);
		u.getRestaurant().add(r);
		userDAO.save(u);
	}

	@Override
	public Long usertotal(){
		return userDAO.userTotales();
	}

	@Override
	public Long userSearchTotal(String name) {
		return userDAO.userTotalesSearch(name);
	}

	@Override
	public List<User> findByName(String name, Pageable p) throws NotFoundException {
		return userDAO.findByName(name.toLowerCase(Locale.getDefault()), p);
	}

	@Override
	public User updateValores(User u, User y) {

		System.out.println("Imprimo el username");
		System.out.println(y.getUsername());
		if(y.getUsername()!= null)
			u.setUsername(y.getUsername());
		if(y.getName()!= null)
			u.setName(y.getName());
		if(y.getPhone()!= null)
			u.setPhone(y.getPhone());
		if(y.getEmail()!= null)
			u.setEmail(y.getEmail());
		if(y.getPassword()!= null)
			u.setPassword(y.getPassword());
		if(y.getRole()!= null)
			u.setRole(y.getRole());

		return u;

	}

//	@Override
//	public List<User> findByName(String name) throws NotFoundException {
//		return userDAO.findByName(name.toLowerCase(Locale.getDefault()));
//	}
}
