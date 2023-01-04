package com.crysten.domain.repository;

import com.crysten.domain.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("select u from Cliente u where upper(trim(u.nomeCompleto)) like %?1%")
    Page<Cliente> findByNomeContaining(String nome, Pageable pageable);

}
