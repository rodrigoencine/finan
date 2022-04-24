package com.example.financeiro.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class CreditoDto {
	
	@NotEmpty(message = "preenchimento obrigatorio")
	private String produto;
	
	@NotNull(message = "preenchimento obrigatorio")
	@Digits(integer = 3, fraction = 2, message = "excedeu numero maximo de digitos")
	private double valor;
	
	@NotEmpty(message = "preenchimento obrigatorio")
	private String data_pedido;
}
