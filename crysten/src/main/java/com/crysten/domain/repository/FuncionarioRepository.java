package com.crysten.domain.repository;

import com.crysten.domain.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("select u from Funcionario u where upper(trim(u.nomeCompleto)) like %?1%")
    Page<Funcionario> findByNomeContaining(String nome, Pageable pageable);
}
