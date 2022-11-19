package com.wildessilva.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wildessilva.cursomc.domain.Cliente;
import com.wildessilva.cursomc.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepositoryImplementation<Pedido, Integer>{
    
    @Transactional(readOnly=true)
    Page<Pedido>  findByCliente(Cliente cliente, Pageable pageRequest);
        
}
