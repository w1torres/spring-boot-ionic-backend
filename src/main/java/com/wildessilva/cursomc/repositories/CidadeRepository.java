package com.wildessilva.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wildessilva.cursomc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepositoryImplementation<Cidade, Integer>{

    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    @Query("SELECT obj FROM Cidade obj WHERE obj.estado.id= :estadoId ORDER BY obj.nome")
    public List<Cidade> findCidades(@Param("estadoId") Integer estado_id);
}
