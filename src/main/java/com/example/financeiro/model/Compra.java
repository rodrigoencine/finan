
package com.example.financeiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "compra", schema = "db_financeiro")
@Getter @Setter 
@AllArgsConstructor 
@EqualsAndHashCode(of = {"id_compra"})
@NoArgsConstructor
@Builder
public class Compra implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_compra;
	
	@Column(nullable = false, length = 13, unique = true)
	private String processo;
	
	@ManyToOne
	@JoinColumn(name = "id_setor")
	private Setor setor;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@JsonManagedReference
	@OneToOne(mappedBy = "compra")
	private Credito credito;
}
