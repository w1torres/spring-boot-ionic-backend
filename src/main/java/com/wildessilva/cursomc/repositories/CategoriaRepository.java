package com.wildessilva.cursomc.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.wildessilva.cursomc.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepositoryImplementation<Categoria, Integer>{

}
