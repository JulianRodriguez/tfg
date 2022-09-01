package com.proyecto.tfg.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User implements UserDetails {
	
	private static final long serialVersionUID = -7987567841L;

	private static final String FIELD_IDROLE = "idRole";
	public static final String FIELD_RESTAURANT = "idUser";
	public static final String FIELD_ROLES = "idRoles";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUser;
	
	@Column
	private String name;

	@Column(unique=true,nullable=false)
	private String email;
	
	@Column(unique=true)
	private String phone;
	
	@Column(unique=true, nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	private Boolean enabled;
	private Boolean tokenExpired;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name=FIELD_IDROLE, nullable=false)
	private Role role;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = FIELD_RESTAURANT, referencedColumnName = FIELD_RESTAURANT)
	private List<Restaurant> restaurant;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
