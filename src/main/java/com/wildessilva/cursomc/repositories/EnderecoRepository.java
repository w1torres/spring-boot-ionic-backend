package com.wildessilva.cursomc.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.wildessilva.cursomc.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepositoryImplementation<Endereco, Integer>{

}
