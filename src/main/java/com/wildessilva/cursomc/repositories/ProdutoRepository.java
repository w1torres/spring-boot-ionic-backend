package com.wildessilva.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wildessilva.cursomc.domain.Categoria;
import com.wildessilva.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepositoryImplementation<Produto, Integer>{
	
	
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
 }
