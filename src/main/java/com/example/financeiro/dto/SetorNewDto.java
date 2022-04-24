package com.example.financeiro.dto;

import java.io.Serializable;

import com.example.financeiro.service.validation.SetorInsert;

@SetorInsert
public class SetorNewDto extends SetorDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SetorNewDto(String sigla, String descricao, Integer id_setor) {
		super(sigla, descricao, id_setor);
		// TODO Auto-generated constructor stub
	}

}
