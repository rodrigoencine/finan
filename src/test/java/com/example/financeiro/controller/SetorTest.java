package com.example.financeiro.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.financeiro.model.Setor;
import com.example.financeiro.repository.SetorRepository;




//@SpringBootTest
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SetorTest {
	
	@Autowired
	private TestEntityManager entityManeger;
	
	@Autowired
	private SetorRepository repo;
	
	public static Setor criarSetor() {
		return Setor.builder().sigla("aaa").descricao("aaaaa").build();
	}

	
	@Test
	public void buscaSetor_PorDescricao() {
		Setor setor = criarSetor();
		setor = this.entityManeger.persist(setor);
		
		Assertions.assertThat(repo.findByDescricao(setor.getDescricao())).isNotNull();
	}
	
	@Test
	public void buscaSetor_PorSigla() {
		Setor setor = criarSetor();
		setor = this.entityManeger.persist(setor);
		
		Assertions.assertThat(repo.findBySigla(setor.getSigla())).isNotNull();
	}
	
	@Test
	public void buscaSetor_PorId() {
		Setor setor = criarSetor();
		setor = this.entityManeger.persist(setor);
		
		Assertions.assertThat(repo.findById(setor.getId_setor()).isPresent());
	}
	@Test
	public void buscaSetor_PorSigla_SetorNaoExiste() {
		Setor setor = this.repo.findBySigla("aaa");
		
		Assertions.assertThat(setor).isNull();
	}
	@Test
	public void persiste_Setor() {
		Setor setor = criarSetor();
		
		setor = this.entityManeger.persist(setor);
		
		Assertions.assertThat(setor.getId_setor()).isNotNull();
		Assertions.assertThat(setor.getSigla()).isEqualTo("aaa");
		Assertions.assertThat(setor.getDescricao()).isEqualTo("aaaaa");
		
		
	}
	//MethodArgumentNotValidException
	
}
