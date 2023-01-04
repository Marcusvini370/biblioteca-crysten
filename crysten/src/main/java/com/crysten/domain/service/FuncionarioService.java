package com.crysten.domain.service;

import com.crysten.domain.model.Funcionario;

import java.util.List;

public interface FuncionarioService {

    List<Funcionario> findAll();

    Funcionario findById(Long idFuncionario);

    Funcionario saveCliente(Funcionario funcionario);

    Funcionario updateCliente(Long idFuncionario, Funcionario funcionario);

    void delete(Long idFuncionario);

}
