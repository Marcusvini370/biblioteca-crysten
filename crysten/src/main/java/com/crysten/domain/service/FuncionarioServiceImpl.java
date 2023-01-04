package com.crysten.domain.service;

import com.crysten.domain.exception.FuncionarioNotFoundException;
import com.crysten.domain.model.Funcionario;
import com.crysten.domain.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService{

    private static final String MSG_FUNCIONARIO_NAO_ENCOTNADO = "Não existe um cadastro de cliente com código %d";


    private FuncionarioRepository funcionarioRepository;

    @Override
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    @Override
    public Funcionario findById(Long idFuncionario) {
        return buscarOuFalhar(idFuncionario);
    }

    @Override
    public Funcionario saveCliente(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario updateCliente(Long idFuncionario, Funcionario funcionario) {
        buscarOuFalhar(idFuncionario);
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public void delete(Long idFuncionario) {
        try {
            funcionarioRepository.deleteById(idFuncionario);

        } catch(EmptyResultDataAccessException e){
            throw new FuncionarioNotFoundException(String.format(MSG_FUNCIONARIO_NAO_ENCOTNADO, idFuncionario));
        }
    }

    public Funcionario buscarOuFalhar(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNotFoundException(String.format(MSG_FUNCIONARIO_NAO_ENCOTNADO, id)));
    }
}
