package com.crysten.domain.service;

import com.crysten.api.dto.FuncionarioDTO;
import com.crysten.api.dto.input.FuncionarioInput;
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
