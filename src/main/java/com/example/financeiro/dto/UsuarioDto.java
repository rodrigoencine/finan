package com.example.financeiro.dto;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.financeiro.enuns.Perfil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor

public class UsuarioDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatorio")
	@Size(min = 3, max = 40)
	private String nome;
	private Integer id_usuario;
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Email(message = "E-mail invalido")
	@Size(min = 3, max = 40)
	private String email;
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String matricula;
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Column(unique = true)
	private String ramal;
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String cargo;
	@Valid
	@NotNull(message = "Preenchimento obrigatorio")
	private SetorDto setor;
	@NotNull(message = "Preenchimento obrigatorio")
	private String senha;
	private Set<Integer> perfis = new HashSet<>();
}
