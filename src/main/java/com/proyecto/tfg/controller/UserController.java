package com.proyecto.tfg.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.tfg.component.mapper.user.UserMapper;
import com.proyecto.tfg.dto.ApiErrorDTO;
import com.proyecto.tfg.dto.user.UserDTO;
import com.proyecto.tfg.dto.user.UserPostDTO;
import com.proyecto.tfg.exception.InvalidRequestException;
import com.proyecto.tfg.exception.NotFoundException;
import com.proyecto.tfg.model.User;
import com.proyecto.tfg.service.user.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value= "/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@GetMapping
	@ApiOperation(notes="Devuelve una lista de usuarios paginado, cada página tendrá un tamaño máximo de 10", tags= { "User" }, value="All user")
	@ApiResponses({ @ApiResponse(code = 200, response= UserDTO.class, message="All users"),
					@ApiResponse(code = 401, response= ApiErrorDTO.class, message="Invalid Request")
	})
	public List<UserDTO> findAll(@RequestParam(defaultValue = "0", required= false ) Integer page,
							 @RequestParam(defaultValue = "10", required= false ) Integer size,
								 @RequestParam(required = false) String searchName) throws InvalidRequestException, NotFoundException {
		final List<User> users;
		if(searchName == null)
		{
			users = userService.findAll(PageRequest.of(page, size));
		}
		else{

			System.out.println(searchName);
			users = userService.findByName(searchName, PageRequest.of(page, size));
		}
		final Long totaldeUser = userService.usertotal();
		System.out.println(totaldeUser);
		System.out.println(users);
		return userMapper.modelToDto(users);
	}

	@GetMapping("/total")
	public Long findTotal() throws NotFoundException {
		final Long totaldeUser = userService.usertotal();
		return totaldeUser;
	}

	@GetMapping("/searchTotal")
	public Long findSearchTotal(@RequestParam(required = false) String searchName) throws NotFoundException {
		final Long totaldeUserSearch = userService.userSearchTotal(searchName);
		return totaldeUserSearch;
	}
	
	@GetMapping("/{idUser}")
	public UserDTO findById(@PathVariable("idUser") Long id) throws NotFoundException {
		final User user = userService.getAndCheck(id);
		return userMapper.modelToDto(user);
	}
	
	@PostMapping
	public UserDTO create(HttpServletRequest request, @RequestBody UserPostDTO dto) throws InvalidRequestException, NotFoundException {
		if(dto.getIdUser() != null) 
			throw new InvalidRequestException("El idUser no se puede recibir en el body");
		final User user = userMapper.dtoToModel(dto);
		final User createUser = userService.create(user);
		return userMapper.modelToDto(createUser);
	}
	
	@PutMapping("/{idUser}")
	public void update(@PathVariable("idUser") Long id, @RequestBody UserDTO dto) throws InvalidRequestException, NotFoundException {
		if(dto.getIdUser() != null) 
			throw new InvalidRequestException("El idUser no se puede recibir en el body");
		final User user = userService.getAndCheck(id);
		System.out.println("Imprimo el dto");
		System.out.println(dto);
		final User userFrom = userMapper.dtoToModel(dto);
		final User userTo = userService.updateValores(user, userFrom);
		userService.update(userTo);
	}
	
	@DeleteMapping("/{idUser}")
	public void delete(@PathVariable("idUser") Long id, @RequestBody UserDTO dto) throws InvalidRequestException, NotFoundException {
		final User user = userService.getAndCheck(id);
        System.out.println(dto.getIdRole());
		if(!userService.isEqual(userMapper.dtoToModel(dto), user)) 
			throw new InvalidRequestException("El usuario recibido no coincide con el almacenado");
		userService.delete(user);
	}
}
