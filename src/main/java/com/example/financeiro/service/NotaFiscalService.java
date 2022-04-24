package com.example.financeiro.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financeiro.dto.NotaFiscalDto;
import com.example.financeiro.model.Compra;
import com.example.financeiro.model.NotaFiscal;
import com.example.financeiro.model.Produto;
import com.example.financeiro.repository.NotaFiscalRepository;
import com.example.financeiro.service.exceptions.DataIntegrityException;
import com.example.financeiro.service.exceptions.ObjectNotFoundException;

@Service
public class NotaFiscalService {
	
	@Autowired
	private CompraService compraService;
	
	@Autowired
	private NotaFiscalRepository notaRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ModelMapper model;
	
	@Transactional
	public NotaFiscal create(NotaFiscalDto dto) {
		if(this.notaRepository.findByNumero(dto.getNumero())!=null) {
			throw new DataIntegrityException("Numero de nota ja cadastrado");
		}
		
		return this.notaRepository.save(this.notaFiscalNew(dto));
	}
	
	public NotaFiscal notaFiscalNew(NotaFiscalDto dto) {

		NotaFiscal nf = this.model.map(dto, NotaFiscal.class);
		
		List<Produto> produtos = dto.getProdutos()
				.stream()
				.map(produto -> new Produto(produto, nf)).collect(Collectors.toList());
		this.setCompraAndProdutos(nf, dto, produtos);
		this.produtoService.create(produtos);

		return nf;
	}
	
	public void setCompraAndProdutos(NotaFiscal nf ,NotaFiscalDto dto, List<Produto> produto) {
		nf.setCompra(this.compraService.findCompra(dto.getCompra()));
		nf.setProdutos(produto);
	}
	
	public NotaFiscal nota(Integer id) {
		return this.notaRepository.findById(id).orElseThrow(() -> 
		new ObjectNotFoundException("Nota fiscal não encontrado! id : " + id + "Tipo" + NotaFiscal.class.getName() )
				);
	}
	
	public List<NotaFiscal> notas(){
		return notaRepository.findAll();				
	}
	
	public void deletarNotaFiscal(Integer id) {
        this.nota(id);
		this.notaRepository.deleteById(id);
	}
	
	public NotaFiscal update (NotaFiscalDto dto, Integer id) {
		NotaFiscal nf = this.nota(id);
		Compra compra = this.compraService.findCompra(dto.getCompra());
		nf.setCompra(compra);
		nf.setData_pagamento(dto.getData_pagamento());
		nf.setNumero(dto.getNumero());
		nf.setValor_total(dto.getValor_total());
		BeanUtils.copyProperties(dto, nf);
		
		return this.notaRepository.save(nf);
	}
}
