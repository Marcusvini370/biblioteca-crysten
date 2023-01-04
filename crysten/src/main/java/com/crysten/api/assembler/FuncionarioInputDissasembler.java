package com.crysten.api.assembler;

import com.crysten.api.dto.input.FuncionarioInput;
import com.crysten.domain.model.Funcionario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioInputDissasembler {

    @Autowired
    private ModelMapper modelMapper;

    public Funcionario toDomainObject(FuncionarioInput funcionarioInput) {
        return modelMapper.map(funcionarioInput, Funcionario.class);
    }

    public void copyToDomainObject(FuncionarioInput funcionarioInput, Funcionario funcionario) {
        modelMapper.map(funcionarioInput, funcionario);
    }

}
