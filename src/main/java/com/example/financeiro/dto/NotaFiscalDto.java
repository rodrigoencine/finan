package com.example.financeiro.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@AllArgsConstructor
public class NotaFiscalDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private Integer id_nota;
	@NotEmpty(message = "Preencimento obrigatorio")
	private String numero;
	
	@NotEmpty(message = "Preencimento obrigatorio")
	private String data_pagamento;
	
	@NotNull(message = "preenchimento obrigatorio")
	@Digits(integer = 3, fraction = 2, message = "excedeu numero maximo de digitos")
	private double valor_total;
	//@NotNull(message = "preenchimento obrigatorio")
	
	private Integer compra;
	@Valid
	private List<ProdutoDto> produtos = new ArrayList<>(); 
}
