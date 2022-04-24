package com.example.financeiro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "nota_fiscal" , schema = "db_financeiro")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id_nota"})
public class NotaFiscal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_nota;
	@Column(nullable = false, length = 50, unique = true)
	private String numero;
	@Column(nullable = false, length = 10)
	private String data_pagamento;
	@Column(nullable = false)
	private double valor_total;
	@ManyToOne
	@JoinColumn(name = "id_compra")
	private Compra compra;
	@JsonManagedReference
	@OneToMany(mappedBy = "nota" , cascade = CascadeType.REMOVE)
	private List<Produto> produtos = new ArrayList<>(); 
	
//	public NotaFiscal() {};
}
