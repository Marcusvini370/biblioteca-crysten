package com.crysten.domain.service;

import com.crysten.domain.model.Endereco;
import com.crysten.domain.model.Funcionario;

import java.util.List;

public interface FuncionarioService {

    List<Funcionario> findAll();

    Funcionario findById(Long idFuncionario);

    Funcionario saveFuncionario(Funcionario funcionario);

    Funcionario updateFuncionario(Long idFuncionario, Funcionario funcionario);

    void delete(Long idFuncionario);

    Endereco consultaCep(String cep);

}
