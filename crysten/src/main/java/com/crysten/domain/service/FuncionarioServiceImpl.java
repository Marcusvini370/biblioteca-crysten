package com.crysten.domain.service;

import com.crysten.domain.model.Funcionario;
import com.crysten.domain.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService{

    private FuncionarioRepository funcionarioRepository;

    @Override
    public List<Funcionario> findAll() {
        return null;
    }

    @Override
    public Funcionario findById(Long idFuncionario) {
        return null;
    }

    @Override
    public Funcionario saveCliente(Funcionario funcionario) {
        return null;
    }

    @Override
    public Funcionario updateCliente(Long idFuncionario, Funcionario funcionario) {
        return null;
    }

    @Override
    public void delete(Long idFuncionario) {

    }
}
