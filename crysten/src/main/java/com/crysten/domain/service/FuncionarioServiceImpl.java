package com.crysten.domain.service;

import com.crysten.api.assembler.FuncionarioInputDissasembler;
import com.crysten.api.assembler.FuncionarioModelAssembler;
import com.crysten.domain.dto.FuncionarioDTO;
import com.crysten.domain.dto.input.FuncionarioInput;
import com.crysten.domain.exception.FuncionarioNotFoundException;
import com.crysten.domain.model.Endereco;
import com.crysten.domain.model.Funcionario;
import com.crysten.domain.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService{

    private static final String MSG_FUNCIONARIO_NAO_ENCOTNADO = "Não existe um cadastro de cliente com código %d";

    private FuncionarioModelAssembler funcionarioModelAssembler;

    private FuncionarioInputDissasembler funcionarioInputDissasembler;

    private FuncionarioRepository funcionarioRepository;

    @Override
    public List<FuncionarioDTO> findAll() {
        return funcionarioModelAssembler.toCollectionModel(funcionarioRepository.findAll());
    }

    @Override
    public FuncionarioDTO findById(Long idFuncionario) {
        return funcionarioModelAssembler.toModel(buscarOuFalhar(idFuncionario));
    }

    @Override
    public FuncionarioDTO saveFuncionario(FuncionarioInput funcionarioInput) {

        Endereco enderecoCep = consultaCep(funcionarioInput.getEndereco().getCep());
        funcionarioInput.setEndereco(enderecoCep);

        Funcionario funcionario = funcionarioInputDissasembler.toDomainObject(funcionarioInput);

        return funcionarioModelAssembler.toModel(funcionarioRepository.save(funcionario));
    }

    @Override
    public FuncionarioDTO updateFuncionario(Long idFuncionario, FuncionarioInput funcionarioInput) {

      Funcionario funcionarioAtual = buscarOuFalhar(idFuncionario);
      Endereco enderecoCep = consultaCep(funcionarioInput.getEndereco().getCep());

          if (funcionarioAtual.getEndereco().getCep() != funcionarioInput.getEndereco().getCep()) {
              funcionarioInputDissasembler.copyToDomainObject(funcionarioInput, funcionarioAtual);
              funcionarioAtual.setEndereco(enderecoCep);

          } else {
              funcionarioInputDissasembler.copyToDomainObject(funcionarioInput, funcionarioAtual);
          }


        return funcionarioModelAssembler.toModel(funcionarioRepository.save(funcionarioAtual));
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
