package com.example.financeiro.enuns;

import com.example.financeiro.service.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum UnidadeMedida {
	Kl(1, "Quilos"),
	Cx(2, "caixa"),
	Un(3, "Unidade");
	
	private Integer id;
	@JsonValue
	private String Descricao;
	
	public static UnidadeMedida toExistsUnidade(Integer id) {
		for(UnidadeMedida uni: UnidadeMedida.values()) {
			if(id.equals(uni.getId())) {
				return uni;
			}
		}
		throw new ObjectNotFoundException("Unidade de medida id: " + id + " Tipo " + UnidadeMedida.class.getName());
	}
}
