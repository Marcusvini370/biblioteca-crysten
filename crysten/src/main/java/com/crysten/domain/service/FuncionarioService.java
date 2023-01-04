package com.crysten.domain.service;

import com.crysten.domain.dto.FuncionarioDTO;
import com.crysten.domain.dto.input.FuncionarioInput;
import com.crysten.domain.model.Endereco;

import java.util.List;

public interface FuncionarioService {

    List<FuncionarioDTO> findAll();

    FuncionarioDTO findById(Long idFuncionario);

    FuncionarioDTO saveFuncionario(FuncionarioInput funcionarioInput);

    FuncionarioDTO updateFuncionario(Long idFuncionario, FuncionarioInput funcionarioInput);

    void delete(Long idFuncionario);

    Endereco consultaCep(String cep);

}
