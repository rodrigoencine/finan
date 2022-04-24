package com.example.financeiro.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "credito", schema = "db_financeiro")
@Getter @Setter
@EqualsAndHashCode(of = {"id_credito"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credito implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_credito;
	
	@Column(nullable = false, length = 100)
	private String produto;
	
	@Column(nullable = false)
	private double valor;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private String data_pedido;
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "id_compra")
	private Compra compra;
}
