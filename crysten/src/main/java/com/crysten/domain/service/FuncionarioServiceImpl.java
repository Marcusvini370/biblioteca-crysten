package com.crysten.domain.service;

import com.crysten.api.assembler.FuncionarioInputDissasembler;
import com.crysten.api.assembler.FuncionarioModelAssembler;
import com.crysten.api.dto.FuncionarioDTO;
import com.crysten.api.dto.input.FuncionarioInput;
import com.crysten.domain.exception.FuncionarioNotFoundException;
import com.crysten.domain.model.Endereco;
import com.crysten.domain.model.Funcionario;
import com.crysten.domain.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<FuncionarioDTO> findAllPage(Pageable pageable) {
        Page<Funcionario> funcionariosPage = funcionarioRepository.findAll(pageable);
        List<FuncionarioDTO> funcionariosDTO = funcionarioModelAssembler.toCollectionModel(funcionariosPage.getContent());
        Page<FuncionarioDTO> funcionarioDTOPage = new PageImpl<>(funcionariosDTO, pageable, funcionariosPage.getTotalElements());
        return funcionarioDTOPage;
    }

    @Override
    public FuncionarioDTO findById(Long idFuncionario) {
        return funcionarioModelAssembler.toModel(buscarOuFalhar(idFuncionario));
    }

    @Override
    public Page<FuncionarioDTO> findByNomeContaining(String nome, Pageable pageable) {
        return funcionarioModelAssembler.toCollectionModelPage(funcionarioRepository.findByNomeContaining(nome.toUpperCase(), pageable));
    }

    @Override
    public FuncionarioDTO saveFuncionario(FuncionarioInput funcionarioInput) {

        Endereco enderecoCep = consultaCep(funcionarioInput.getEndereco().getCep());
        funcionarioInput.getEndereco().setUf(enderecoCep.getUf());
        funcionarioInput.getEndereco().setLocalidade(enderecoCep.getLocalidade());
        funcionarioInput.getEndereco().setBairro(enderecoCep.getBairro());
        funcionarioInput.getEndereco().setComplemento(enderecoCep.getComplemento());
        funcionarioInput.getEndereco().setLogradouro(enderecoCep.getLogradouro());

        Funcionario funcionario = funcionarioInputDissasembler.toDomainObject(funcionarioInput);

        return funcionarioModelAssembler.toModel(funcionarioRepository.save(funcionario));
    }

    @Override
    public FuncionarioDTO updateFuncionario(Long idFuncionario, FuncionarioInput funcionarioInput) {

      Funcionario funcionarioAtual = buscarOuFalhar(idFuncionario);
      Endereco enderecoCep = consultaCep(funcionarioInput.getEndereco().getCep());

          if (funcionarioAtual.getEndereco().getCep() != funcionarioInput.getEndereco().getCep()) {
              funcionarioInputDissasembler.copyToDomainObject(funcionarioInput, funcionarioAtual);

              funcionarioInput.getEndereco().setUf(enderecoCep.getUf());
              funcionarioInput.getEndereco().setLocalidade(enderecoCep.getLocalidade());
              funcionarioInput.getEndereco().setBairro(enderecoCep.getBairro());
              funcionarioInput.getEndereco().setComplemento(enderecoCep.getComplemento());
              funcionarioInput.getEndereco().setLogradouro(enderecoCep.getLogradouro());

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
