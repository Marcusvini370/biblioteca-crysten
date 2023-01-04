package com.crysten.domain.service;

import com.crysten.domain.exception.FuncionarioNotFoundException;
import com.crysten.domain.model.Endereco;
import com.crysten.domain.model.Funcionario;
import com.crysten.domain.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public Funcionario saveFuncionario(Funcionario funcionario) {

        Endereco enderecoCep = consultaCep(funcionario.getEndereco().getCep());

        funcionario.setEndereco(enderecoCep);

        return funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario updateFuncionario(Long idFuncionario, Funcionario funcionario) {

      Funcionario funcionarioAtual = buscarOuFalhar(idFuncionario);
      Endereco enderecoCep = consultaCep(funcionario.getEndereco().getCep());

          if (funcionarioAtual.getEndereco().getCep() != funcionario.getEndereco().getCep()) {
              BeanUtils.copyProperties(funcionario, funcionarioAtual, "id");
              funcionarioAtual.setEndereco(enderecoCep);

          } else {
              BeanUtils.copyProperties(funcionario, funcionarioAtual, "id");
          }


        return funcionarioRepository.save(funcionarioAtual);
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

    public Endereco consultaCep(String cep){
        return new RestTemplate().getForEntity("https://viacep.com.br/ws/ " + cep + "/json/", Endereco.class).getBody();
    }

}
