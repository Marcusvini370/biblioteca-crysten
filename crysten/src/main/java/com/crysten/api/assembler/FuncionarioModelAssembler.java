package com.crysten.api.assembler;

import com.crysten.api.dto.FuncionarioDTO;
import com.crysten.domain.model.Funcionario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FuncionarioModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FuncionarioDTO toModel(Funcionario funcionario) {
        return modelMapper.map(funcionario, FuncionarioDTO.class);
    }

    public List<FuncionarioDTO> toCollectionModel(List<Funcionario> Funcionarios) {
        return Funcionarios.stream()
                .map(funcionario -> toModel(funcionario))
                .collect(Collectors.toList());
    }

    public Page<FuncionarioDTO> toCollectionModelPage(Page<Funcionario> page) {
        return page.map(funcionario -> toModel(funcionario));
    }

}
