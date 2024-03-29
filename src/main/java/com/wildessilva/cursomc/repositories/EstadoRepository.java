package com.wildessilva.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.wildessilva.cursomc.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepositoryImplementation<Estado, Integer>{
       
    public List<Estado> findAllByOrderByNome();
}
