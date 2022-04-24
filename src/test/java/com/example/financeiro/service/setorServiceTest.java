package com.example.financeiro.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.hibernate.hql.internal.ast.tree.IsNotNullLogicOperatorNode;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.financeiro.dto.SetorDto;
import com.example.financeiro.model.Setor;
import com.example.financeiro.repository.SetorRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class setorServiceTest {
	
	@MockBean
	private SetorService service;
	
	@MockBean
	private SetorRepository repo;
	
	@Before
	public void setUp() {
		service = new SetorService();
	}
	@Test
	public void existe_Setor_Sigla() {
		
		String sigla = "bbb";
		String descricao = "testando";
		//SetorDto setor = SetorDto.builder().sigla(sigla).descricao(descricao).id(1).build();
		//Mockito.doNothing().when(service).converterSetor(Mockito.any(SetorDto.class));
		//Mockito.when(repo.save(Mockito.any(Setor.class))).thenReturn(setor);
		//Mockito.when(service.converterSetor(Mockito.any(SetorDto.class))).thenReturn(null);
		//Setor setorSalvo = service.converterSetor(setor);
		
		//Assertions.assertThat(setorSalvo ).isNull();
	}
	public void nao_exist_Setor() {
		
	}
}
