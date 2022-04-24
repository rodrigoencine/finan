package com.example.financeiro.dto;

import java.io.Serializable;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Builder
@AllArgsConstructor

public class SetorDto implements Serializable{
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Size(min = 3, max =5)
	private String sigla;
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Size(min = 4, max = 50)
	private String descricao;
	private Integer id_setor;
}
