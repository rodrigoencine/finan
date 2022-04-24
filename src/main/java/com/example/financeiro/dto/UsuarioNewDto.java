package com.example.financeiro.dto;


import java.util.Set;

import com.example.financeiro.enuns.Perfil;
import com.example.financeiro.service.validation.UsuarioInsert;


@UsuarioInsert
public class UsuarioNewDto extends UsuarioDto{
	public UsuarioNewDto(String nome, Integer id_usuario, String email, String matricula, String ramal, String cargo,
			SetorDto setor,String senha,Set<Integer>perfis) {
		super(nome, id_usuario, email, matricula, ramal, cargo, setor, senha, perfis);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
