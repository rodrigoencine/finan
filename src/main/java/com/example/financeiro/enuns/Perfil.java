package com.example.financeiro.enuns;

import com.example.financeiro.service.exceptions.ObjectNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Perfil {

	ADMIN (1, "ROLE_ADMIN"),
	USUARIO (2, "ROLE_CLIENTE");
	
	
	private Integer cod;

	private String descricao;

	public static Perfil isPerfil(Integer id) {
		for(Perfil perfil : Perfil.values()) {
			if(id.equals(perfil.getCod())) {
				return perfil;
			}
		}
		throw new ObjectNotFoundException(
				"Perfil não existe! Id: " + id + " Tipo " + Perfil.class.getName());
	}
}
