package com.example.financeiro.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CompraDto {

	@NotEmpty(message = "preenchimento obrigatorio")
	private String processo;
	
	@Valid
	@NotNull(message = "preenchimento obrigatorio")
	private SetorDto setor;
	
	@Valid
	@NotNull(message = "preenchimento obrigatorio")
	private UsuarioDto usuario;
	
	@Valid
	@NotNull(message = "preenchimento obrigatorio")
	private CreditoDto credito;
}
