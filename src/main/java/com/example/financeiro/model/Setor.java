package com.example.financeiro.model;
 
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "setor" , schema = "db_financeiro")
@Setter @Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode( of = {"id_setor"})
public class Setor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_setor;
	
	@Column(nullable = false, unique = true, length = 5)
	private String sigla;
	
	@Column(nullable = false, unique = true, length = 50)
	private String descricao;
}
