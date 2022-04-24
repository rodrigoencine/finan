package com.example.financeiro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.financeiro.dto.ProdutoDto;
import com.example.financeiro.enuns.UnidadeMedida;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "produto")
@Getter @Setter
@Builder
@EqualsAndHashCode(of = {"id_produto"})
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_produto;
	
	@Column(nullable = false, length = 50)
	private String descricao;
	
	//@Enumerated(EnumType.STRING)
	private UnidadeMedida unidade;
	
	@Column(nullable = false)
	private double valor_unitario;
	
	@Column(nullable = false)
	private int quantidade;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "id_nota")
	private NotaFiscal nota;	
	
	public Produto (ProdutoDto dto, NotaFiscal nf) {
		this.descricao = dto.getDescricao();
		this.nota = nf;
		this.quantidade = dto.getQuantidade();
		this.unidade = UnidadeMedida.toExistsUnidade(dto.getUnidade());
		this.valor_unitario = dto.getValorUnitario();
	}
	
	
}
 