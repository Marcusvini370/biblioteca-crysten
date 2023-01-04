package com.crysten.domain.service;

import com.crysten.api.dto.FuncionarioDTO;
import com.crysten.api.dto.input.FuncionarioInput;
import com.crysten.domain.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FuncionarioService {

    List<FuncionarioDTO> findAll();

    Page<FuncionarioDTO> findAllPage(Pageable pageable);

    FuncionarioDTO findById(Long idFuncionario);

    Page<FuncionarioDTO> findByNomeContaining(String nome, Pageable pageable);

    FuncionarioDTO saveFuncionario(FuncionarioInput funcionarioInput);

    FuncionarioDTO updateFuncionario(Long idFuncionario, FuncionarioInput funcionarioInput);

    void delete(Long idFuncionario);

    Endereco consultaCep(String cep);

}
