package com.proyecto.tfg.dto.user;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConnectedDTO {

	private String username;
	private String idSession;
	private String rolename;
	private List<String> grantedAuthorities;
}
