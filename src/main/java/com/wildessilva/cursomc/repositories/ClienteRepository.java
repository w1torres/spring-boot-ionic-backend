package com.wildessilva.cursomc.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wildessilva.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepositoryImplementation<Cliente, Integer>{

	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
}
