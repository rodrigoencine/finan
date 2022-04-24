package com.example.financeiro.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.financeiro.enuns.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="usuario", schema = "db_financeiro")
@EqualsAndHashCode(of = {"id_usuario"})
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_usuario;
	
	@Column(nullable = false, unique = true, length = 5)
	private String matricula;
	
	@Column(nullable = false, length = 40)
	private String nome;
	
	@Column(nullable = false, unique = true, length = 40)
    private String email;
	
	@Column(nullable = false, length = 4)
	private String ramal;
	
	@Column(nullable = false)
	private String cargo;
	
	@ManyToOne
	@JoinColumn(name = "idsetor")
	private Setor setor;
	
	@JsonIgnore
	private String senha;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	public Set<Perfil> getPerfis(){
		return perfis.stream()
				.map( perfil 
						-> Perfil.isPerfil(perfil)).collect(Collectors.toSet());
	}
	
//	public void addPerfil(Perfil perfil) {
//		perfis.add(perfil.getCod());
//	}
}