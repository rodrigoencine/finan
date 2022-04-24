package com.example.financeiro.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.financeiro.dto.CompraDto;
import com.example.financeiro.model.Compra;
import com.example.financeiro.model.Credito;
import com.example.financeiro.model.Usuario;
import com.example.financeiro.queryDto.CompraQuery;
import com.example.financeiro.repository.CompraRepository;
import com.example.financeiro.repository.CreditoRepository;
import com.example.financeiro.security.UsuarioSecurity;
import com.example.financeiro.security.UsuarioServiceSecurity;
import com.example.financeiro.service.exceptions.AuthorizationException;
import com.example.financeiro.service.exceptions.DataIntegrityException;
import com.example.financeiro.service.exceptions.ObjectNotFoundException;

@Service
public class CompraService{
	
	@Autowired
	private ModelMapper model;
	
	@Autowired	
	private CreditoRepository creditorepository;

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	@Transactional
	public Compra create(CompraDto compraDto) {	
		Compra compra = this.compraRepository.findByProcesso(compraDto.getProcesso());
		if(compra!=null) {
			throw new DataIntegrityViolationException("Numero de processo ja cadastrado");
		}
		return this.compraRepository.save(toCompra(compraDto));
	}
	
	public Compra toCompra(CompraDto dto) {
		
		Compra compra = this.model.map(dto, Compra.class);
		Credito credito = creditorepository.save(this.creditoBuilder(compra, dto));
		compra.setCredito(credito);
		return compra;
	}
	public Credito creditoBuilder(Compra compra, CompraDto dto) {
		
		return Credito.builder()
				.compra(compra)
				.data_pedido(dto.getCredito().getData_pedido())
				.produto(dto.getCredito().getProduto())
				.valor(dto.getCredito().getValor()).build();
		
	}
	
	public Compra findCompra(Integer id) {
		
		return this.compraRepository.findById(id).
				orElseThrow(() -> 
				new ObjectNotFoundException("Compra não encontrado! id : " + id + "Tipo" + Compra.class.getName() ));
	}
	

	public void deletar(Integer id) {
		this.findCompra(id);
		try {
			this.compraRepository.deleteById(id);
		} catch (DataIntegrityViolationException  e) {
			throw new DataIntegrityException("Compra vinculado");
		}
	}

	public Page<Compra> findPages(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest request = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return this.compraRepository.findAll(request);
	}
	public Page<CompraQuery> findPage(String processo, String nome, String produto,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest request = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return this.compraRepository.search(processo,nome,produto, request);
	}
	
	public Page<Compra> findCompraUsuario(Integer page, Integer linesPerPage, String orderBy, String direction){
		UsuarioSecurity usuario = UsuarioServiceSecurity.authenticated();
		if(usuario == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		PageRequest request = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Usuario user = usuarioService.findUser(usuario.getId());
		return this.compraRepository.findByUsuario(user, request);
	}
	
}
