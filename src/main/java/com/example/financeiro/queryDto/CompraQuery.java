package com.example.financeiro.queryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraQuery {
	private String processo;
	private String nome;
	private String produto;
}
