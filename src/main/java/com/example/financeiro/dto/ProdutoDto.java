package com.example.financeiro.dto;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "campo obrigatorio preenchimento")
	@Size(min= 4)
	private String descricao;
	
	@NotNull(message = "preenchimento obrigatorio")
	private Integer unidade;
	
	@NotNull(message = "preenchimento obrigatorio")
	@Digits(integer = 3, fraction = 2, message = "excedeu numero maximo de digitos")
	private double valorUnitario;
	
	@NotNull(message = "preenchimento obrigatorio")
	private int quantidade;
}
